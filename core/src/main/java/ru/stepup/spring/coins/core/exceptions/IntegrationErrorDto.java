package ru.stepup.spring.coins.core.exceptions;

import java.time.LocalDateTime;

public record IntegrationErrorDto(String code, String message, LocalDateTime date) {
}
