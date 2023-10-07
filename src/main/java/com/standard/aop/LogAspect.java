package com.standard.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.dto.response.ApiResponse;
import com.standard.util.CustomLogUtil;
import com.standard.util.LogInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class LogAspect {

    private final ObjectMapper objectMapper;

    @Pointcut("execution(public * com.standard.controller.*.*(..))")
    public void controller() {}

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {}

    @AfterReturning(pointcut = "controller()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (servletRequestAttributes != null) {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                ApiResponse returnValue = getValue(result);
                if (returnValue != null) {
                    LogInfo logInfo = new LogInfo(null, null, LogLevel.INFO, request, returnValue);
                    log.info(CustomLogUtil.writeValueAsString(objectMapper, logInfo));
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private ApiResponse getValue(Object result) {
        if (result instanceof ResponseEntity
                && ((ResponseEntity<?>) result).getBody() instanceof ApiResponse) {
            return (ApiResponse) ((ResponseEntity<?>) result).getBody();
        }
        return null;
    }

}
