package com.konduto.challenge.controller;

import com.konduto.challenge.controller.api.TransactionControllerApi;
import com.konduto.challenge.dto.FeaturesResponseDto;
import com.konduto.challenge.dto.TransactionDto;
import com.konduto.challenge.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling transaction-related requests.
 * Provides an endpoint to calculate features based on a transaction.
 */
@RestController
@RequestMapping("/v1")
public class TransactionController implements TransactionControllerApi {

    private final CalculatorService calculatorService;

    public TransactionController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Endpoint to calculate features for a given transaction.
     *
     * @param transaction the transaction details
     * @return a response entity containing the calculated features
     */
    @PostMapping("/features")
    public ResponseEntity<FeaturesResponseDto> calculateFeatures(@RequestBody TransactionDto transaction) {
        return ResponseEntity.ok(calculatorService.calculateFeatures(transaction));
    }

}
