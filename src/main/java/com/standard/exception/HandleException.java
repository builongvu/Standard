package com.standard.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.constant.ErrorEnum;
import com.standard.dto.response.ApiResponse;
import com.standard.util.CustomLogUtil;
import com.standard.util.LogInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class HandleException {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(ErrorEnum.INTERNAL_SERVER_ERROR.getStatus())
                .message(ErrorEnum.INTERNAL_SERVER_ERROR.getMessage())
                .build();
        log.error(CustomLogUtil.writeValueAsString(objectMapper,
                new LogInfo(null, null, LogLevel.ERROR, request, apiResponse, ex)), ex);
        return ResponseEntity.status(ErrorEnum.INTERNAL_SERVER_ERROR.getStatus()).body(apiResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(ErrorEnum.UNAUTHORIZED.getStatus())
                .message(ex.getMessage())
                .build();
        log.error(CustomLogUtil.writeValueAsString(objectMapper,
                new LogInfo(null, null, LogLevel.ERROR, request, apiResponse, ex)), ex);
        return ResponseEntity.status(ErrorEnum.UNAUTHORIZED.getStatus()).body(apiResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .build();
        log.error(CustomLogUtil.writeValueAsString(objectMapper,
                new LogInfo(null, null, LogLevel.ERROR, request, apiResponse, ex)), ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse> handleApplicationException(ApplicationException ex, HttpServletRequest request) throws JsonProcessingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .build();
        log.error(CustomLogUtil.writeValueAsString(objectMapper,
                new LogInfo(null, null, LogLevel.ERROR, request, apiResponse, ex)));
        return ResponseEntity.status(ex.getStatus()).body(apiResponse);
    }

}

