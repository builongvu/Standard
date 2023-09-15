package com.standard.exception;

import com.standard.constant.ErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationException extends RuntimeException {

    private Integer status;
    private String message;

    public ApplicationException(ErrorEnum errorEnum, Object... params) {
        this.status = errorEnum.getStatus();
        this.message = String.format(errorEnum.getMessage(), params);
    }

}
