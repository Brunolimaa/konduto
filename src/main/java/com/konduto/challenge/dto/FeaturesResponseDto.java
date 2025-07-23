package com.konduto.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * DTO for the response containing features of a transaction.
 * Includes total amount, CPF rating, and credit card score.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeaturesResponseDto(
        @JsonProperty("TOTAL_AMOUNT") BigDecimal totalAmount,
        @JsonProperty("CPF_RATING") Double cpfRating,
        @JsonProperty("CC_SCORE") BigDecimal ccScore
) {}