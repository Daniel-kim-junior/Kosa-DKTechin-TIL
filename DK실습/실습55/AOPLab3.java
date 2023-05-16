package com.example.springedu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class AOPLab3 {
    @Around("execution(* com.example.springedu.controller.EmpController.*(..))")
    public void timeLog(ProceedingJoinPoint jp) throws Throwable  {
        long l1 = System.currentTimeMillis();
        jp.proceed();
        long l2 = System.currentTimeMillis();
        log.info("{} - {} - {}", jp.getTarget().getClass().getName() ,"수행시간(밀리초)", l2-l1);
    }
}
