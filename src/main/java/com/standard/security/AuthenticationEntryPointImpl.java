package com.standard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.dto.response.ApiResponse;
import com.standard.util.CustomLogUtil;
import com.standard.util.LogInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

//@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver handlerExceptionResolver;
//    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ApiResponse apiResponse = ApiResponse.builder()
//                .status(HttpServletResponse.SC_UNAUTHORIZED)
//                .message(authException.getMessage())
//                .build();
//        log.error(CustomLogUtil.writeValueAsString(objectMapper,
//                new LogInfo(null, null, LogLevel.ERROR, request, apiResponse)));

        handlerExceptionResolver.resolveException(request, response, null, authException);
    }

}
