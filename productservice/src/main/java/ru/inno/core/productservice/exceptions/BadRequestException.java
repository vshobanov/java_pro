package ru.inno.core.productservice.exceptions;

public class BadRequestException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public BadRequestException(String message, String code) {
        super(message);
        this.code = code;
    }
}
