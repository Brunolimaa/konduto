package com.konduto.challenge.service;

import com.konduto.challenge.dto.FeaturesResponseDto;
import com.konduto.challenge.dto.TransactionDto;

public interface CalculatorService {
    FeaturesResponseDto calculateFeatures(TransactionDto transaction);
}
