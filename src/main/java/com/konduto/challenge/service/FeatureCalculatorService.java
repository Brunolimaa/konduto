package com.konduto.challenge.service;

import com.konduto.challenge.client.CpfRatingApiMockExternalClient;
import com.konduto.challenge.client.CpfRatingClient;
import com.konduto.challenge.dto.CpfRatingEnum;
import com.konduto.challenge.dto.CpfRatingResponseDto;
import com.konduto.challenge.dto.FeaturesResponseDto;
import com.konduto.challenge.dto.TransactionDto;
import com.konduto.challenge.repository.CreditCardTransactionRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service for calculating features for transactions.
 */
@Service
public class FeatureCalculatorService implements CalculatorService {

    private static final Logger log = LoggerFactory.getLogger(FeatureCalculatorService.class);

    public static final double RATING_GOOD = 0.0;
    public static final double RATING_BAD = 1.0;

    private final CpfRatingClient cpfRatingClient;
    private final CpfRatingApiMockExternalClient cpfRatingApiMockExternalClient;
    private final CreditCardTransactionRepository creditCardTransactionRepository;

    public FeatureCalculatorService(CpfRatingClient cpfRatingClient,
                                    CreditCardTransactionRepository creditCardTransactionRepository,
                                    CpfRatingApiMockExternalClient cpfRatingApiMockExternalClient) {
        this.cpfRatingClient = cpfRatingClient;
        this.creditCardTransactionRepository = creditCardTransactionRepository;
        this.cpfRatingApiMockExternalClient = cpfRatingApiMockExternalClient;
    }

    /**
     * Calculates the features for a transaction, including CPF_RATING and CC_SCORE.
     *
     * @param transaction
     * @return
     */
    public FeaturesResponseDto calculateFeatures(TransactionDto transaction) {

        Double cpfRating = getCpfRating(transaction);

        BigDecimal ccScore = creditCardTransactionRepository.calculateCcScore(transaction.cpf());

        if (ccScore == null) {
            ccScore = BigDecimal.ZERO;
            log.info("CC_SCORE calculated as zero for CPF: {} due to no prior transactions or null sum.", transaction.cpf());
        }

        FeaturesResponseDto response = new FeaturesResponseDto(transaction.totalAmount(), cpfRating, ccScore);
        log.info("Features calculated successfully for CPF {}: {}", transaction.cpf(), response);
        return response;
    }

    /**
     * Fetches the CPF rating for a given transaction.
     * This method calls the Trampo Certo CPF rating service
     *
     * @param transaction
     * @return
     */
    private Double getCpfRating(TransactionDto transaction) {
        Double cpfRating = null;
        try {
            CpfRatingResponseDto cpfResponse = getCpfRatingResponse(transaction.cpf());
            if (cpfResponse != null && cpfResponse.rating() != null) {
                cpfRating = switch (CpfRatingEnum.fromString(cpfResponse.rating())) {
                    case GOOD -> RATING_GOOD;
                    case BAD -> RATING_BAD;
                    case UNKNOWN -> null;
                };
            }
        } catch (FeignException e) {
            log.error("Error CPF_RATING for the CPF: {}", transaction.cpf(), e);
        }
        return cpfRating;
    }

    /**
     * Fetches the CPF rating response from the external service.
     *
     * @param cpf
     * @return
     */
    private CpfRatingResponseDto getCpfRatingResponse(String cpf) {
        CpfRatingResponseDto cpfResponse = null;

        try {

            cpfResponse = cpfRatingClient.getCpfRating(cpf);
            log.debug("Received CPF rating from primary client for CPF {}", cpf);

        } catch (Exception e) {
            log.error("Error fetching CPF rating for CPF: {}", cpf);

            log.info("Using mock external API for CPF rating");
            cpfResponse = cpfRatingApiMockExternalClient.getCpfRating(cpf);
        }
        return cpfResponse;
    }
}