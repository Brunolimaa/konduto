package com.konduto.challenge.controller.api;

import com.konduto.challenge.dto.FeaturesResponseDto;
import com.konduto.challenge.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * API interface for transaction-related operations.
 */
@Tag(name = "Transaction Controller", description = "Controller for handling transaction-related operations")
public interface TransactionControllerApi {

    /**
     * Calculates features for a given transaction.
     *
     * @param transaction
     * @return
     */
    @ApiResponse(
            responseCode = "200",
            description = "Successfully calculated features for the transaction"
    )
    @Operation(
            summary = "Calculate features for a transaction",
            description = "Analyzes the provided transaction details and returns calculated features, including risk score and other relevant attributes."
    )
    @RequestBody(
            description = "Transaction details to calculate features for",
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TransactionDto.class),
                    examples = @ExampleObject(
                            name = "Transaction Example",
                            summary = "Typical Transaction for Feature Calculation",
                            value = "{\n" +
                                    "  \"total_amount\": 87.99,\n" +
                                    "  \"cpf\": \"94548325069\",\n" +
                                    "  \"cc\": {\n" +
                                    "    \"bin\": \"548110\",\n" +
                                    "    \"last4\": \"2104\"\n" +
                                    "  }\n" +
                                    "}"
                    )
            )
    )
    ResponseEntity<FeaturesResponseDto> calculateFeatures(@Parameter(description = "transaction details") TransactionDto transaction);
}
