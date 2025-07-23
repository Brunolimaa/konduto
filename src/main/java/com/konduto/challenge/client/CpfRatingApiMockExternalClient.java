package com.konduto.challenge.client;

import com.konduto.challenge.dto.CpfRatingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Feign client for accessing an external API that provides CPF ratings.
 */
@FeignClient(name = "externalApiMockRating", url = "${api.mockexternal.api.url}")
public interface CpfRatingApiMockExternalClient {

    @GetMapping("/cpf_query")
    CpfRatingResponseDto getCpfRating(@RequestParam("cpf") String cpf);
}