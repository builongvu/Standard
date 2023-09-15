package com.standard.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "%s not found with %s:%d"),
    NULL_REQUEST_DATA(HttpStatus.BAD_REQUEST.value(), "%s must be not null");

    private final String message;
    private final Integer status;

    ErrorEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
