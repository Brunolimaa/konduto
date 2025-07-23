package com.konduto.challenge.dto;

/** * DTO for the response from the CPF rating service. * Contains the CPF and its rating. */
public record CreditCardDto(
        String bin,
        String last4
) {}