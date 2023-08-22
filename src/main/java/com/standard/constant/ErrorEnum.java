package com.standard.constant;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    RESOURCE_NOT_FOUND("resource not found %s:%s");

    private final String message;

    ErrorEnum(String message) {
        this.message = message;
    }
}
