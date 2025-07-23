package com.konduto.challenge.client;

import com.konduto.challenge.dto.CpfRatingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Client for the Trampo Certo CPF rating service.
 * This client is used to fetch the rating of a CPF.
 */
@FeignClient(name = "trampoCertoRating", url = "${trampo.certo.api.url}")
public interface CpfRatingClient {

    @GetMapping("/cpf_query")
    CpfRatingResponseDto getCpfRating(@RequestParam("cpf") String cpf);
}