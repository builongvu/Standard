package com.standard.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.dto.ApiResponse;
import com.standard.util.CustomLogUtil;
import com.standard.util.LogInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class HandleException {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse> handleApplicationException(ApplicationException ex, HttpServletRequest request) throws JsonProcessingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        log.error(CustomLogUtil.writeValueAsString(objectMapper,
                new LogInfo(null, null, LogLevel.ERROR, request, apiResponse)));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(ArithmeticException.class)
    public void abc(ArithmeticException ex) {
        System.out.println(ex.getMessage());
    }

}

