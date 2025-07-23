package com.konduto.challenge.dto;

public enum CpfRatingEnum {
    GOOD,
    BAD,
    UNKNOWN;

    public static CpfRatingEnum fromString(String value) {
        return switch (value.toLowerCase()) {
            case "good" -> GOOD;
            case "bad" -> BAD;
            default -> UNKNOWN;
        };
    }
}