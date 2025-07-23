package com.konduto.challenge;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.konduto.challenge.dto.FeaturesResponseDto;
import com.konduto.challenge.dto.TransactionDto;
import com.konduto.challenge.service.FeatureCalculatorService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FeatureCalculatorServiceTest {
    private static final Logger log = LoggerFactory.getLogger(FeatureCalculatorServiceTest.class);

    @Autowired
    private FeatureCalculatorService featureCalculatorService;

    @Test
    void testCalculateFeatures() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String transactionJson = "{\n" +
                "\"total_amount\": 87.99,\n" +
                "\"cpf\":\"94548325069\",\n" +
                "\"cc\": {\n" +
                "\"bin\": \"548110\",\n" +
                "\"last4\":\"2104\"\n" +
                "}\n" +
                "}";

        TransactionDto transaction = objectMapper.readValue(transactionJson, TransactionDto.class);
        FeaturesResponseDto result = featureCalculatorService.calculateFeatures(transaction);

        log.info("Result for known CPF: {}", objectMapper.writeValueAsString(result));

        String transactionUnknownCpfJson = "{\"total_amount\": 50.00, \"cpf\":\"99988877766\", \"cc\": {\"bin\": \"123456\", \"last4\":\"7890\"}}";
        TransactionDto unknownCpfTransaction = objectMapper.readValue(transactionUnknownCpfJson, TransactionDto.class);
        FeaturesResponseDto resultUnknownCpf = featureCalculatorService.calculateFeatures(unknownCpfTransaction);

        log.info("Result for unknown CPF: {}", objectMapper.writeValueAsString(resultUnknownCpf));

        assertEquals(BigDecimal.valueOf(3.0), result.ccScore());
        assertEquals(BigDecimal.ZERO, resultUnknownCpf.ccScore());

    }
}