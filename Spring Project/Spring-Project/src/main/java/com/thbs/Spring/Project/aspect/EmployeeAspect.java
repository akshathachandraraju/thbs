package com.thbs.Spring.Project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeAspect {
    @Before("execution(* com.thbs.Spring.Project.service.EmployeeService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.print("Before ");
        System.out.print(joinPoint.getSignature().getName() + " called with ");
    }

    @AfterReturning(pointcut = "execution(* com.thbs.Spring.Project.service.EmployeeService.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        System.out.print("After ");
        System.out.print(joinPoint.getSignature().getName());
        System.out.println(" result is " + result);
    }
}
