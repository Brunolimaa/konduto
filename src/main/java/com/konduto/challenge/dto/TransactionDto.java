package com.konduto.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 DTO for a transaction.
 Contains the total amount, CPF, and credit card details.
 */
public record TransactionDto(
        @JsonProperty("total_amount") BigDecimal totalAmount,
        String cpf,
        CreditCardDto cc
) {}