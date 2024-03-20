package ru.inno.core.limits.exceptions;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException {
    private final String code;


    public BadRequestException(String message, String code) {
        super(message);
        this.code = code;
    }
}
