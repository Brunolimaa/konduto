package com.konduto.challenge.controller.api;

import com.konduto.challenge.dto.CpfRatingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API interface for the mock external API that provides CPF ratings.
 * This interface defines the contract for retrieving CPF ratings from a mock external service.
 */
@Tag(name = "Mock External API Controller", description = "Controller for simulating an external API that provides CPF ratings")
public interface MockExternalApiControllerApi {

    /**
     * Retrieves the rating for a given CPF number from a mock external API.
     *
     * @param cpf
     * @return
     */
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved CPF rating"
    )
    @Operation(
            summary = "Get CPF rating",
            description = "Retrieves the rating for a given CPF number from a mock external API."
    )
    CpfRatingResponseDto getCpf(@Parameter(description = "cpf")  String cpf);
}
