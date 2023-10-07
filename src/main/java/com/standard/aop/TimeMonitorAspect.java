package com.standard.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] inputs = joinPoint.getArgs();

        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long executionTime = System.nanoTime() - start;

        log.info("Method " + className + "." + methodName + " with input " + Arrays.toString(inputs) +
                " execution time: " + executionTime + " nanoseconds");

        return proceed;
    }

}
