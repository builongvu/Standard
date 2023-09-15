package com.standard.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class LogAspect {

    @Pointcut("execution(* com.standard.controller.*.*(..))")
    public void controller() {}

    @AfterReturning(pointcut = "controller()")
    public void logAfterReturning(JoinPoint joinPoint) {
        System.out.println("hello");
    }

}
