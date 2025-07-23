package com.konduto.challenge.controller;

import com.konduto.challenge.dto.CpfRatingEnum;
import com.konduto.challenge.dto.CpfRatingResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mock controller for simulating an external API that provides CPF ratings.
 */
@RestController
@RequestMapping("/v1/api")
public class MockExternalApiController {

    /**
     * Endpoint to retrieve the CPF rating based on a given CPF number.
     *
     * @param cpf the CPF number to query
     * @return a response containing the CPF rating
     */
    @GetMapping("/cpf_query")
    public CpfRatingResponseDto getCpf(@RequestParam("cpf") String cpf) {
        return new CpfRatingResponseDto(
                "94548325069",
                CpfRatingEnum.GOOD.name()
        );
    }
}