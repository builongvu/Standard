package com.standard.exception;

import com.standard.constant.ErrorEnum;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private String message;

    public ApplicationException(ErrorEnum errorEnum, String... params) {
        this.message = String.format(errorEnum.getMessage(), params);
    }

}
