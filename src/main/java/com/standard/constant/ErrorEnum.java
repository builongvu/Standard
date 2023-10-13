package com.standard.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "%s not found with %s:%s"),
    NULL_REQUEST_DATA(HttpStatus.BAD_REQUEST.value(), "%s must be not null"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Wrong username or password"),
    DUPLICATE(HttpStatus.CONFLICT.value(), "%s is already taken"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED.value(), "Token was expired, please make a new login request");

    private final String message;
    private final Integer status;

    ErrorEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
