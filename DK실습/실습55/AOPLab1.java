package com.example.springedu.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLab1 {
    @Before("execution(* com.example.springedu.controller.HelloController.*(..))")
    public void before() {
        System.out.println("[AOP] hello 수행전");
    }
    @After("execution(* com.example.springedu.controller.HelloController.*(..))")
    public void after() {
        System.out.println("[AOP] hello 수행후");
    }
}
