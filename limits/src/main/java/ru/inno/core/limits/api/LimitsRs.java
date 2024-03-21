package ru.inno.core.limits.api;

public record LimitsRs(
        long availableLimit,
        String code,
        String message) {

}
