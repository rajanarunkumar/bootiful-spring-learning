package com.example.demo.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AdvisorForDataEntitlements {

  @Around(value = "execution(* com.example.demo.repository.jpa.EmployeeRepository+.*(..)) && args(parameters)", argNames = "pjp, parameters")
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public Object advisor(ProceedingJoinPoint pjp, Object[] parameters) {
    log.info("Printing Parameters {}", parameters);
    Object object = null;
    try {
      object = pjp.proceed(parameters);
    } catch (Throwable throwable) {
      log.error("Exception occurred while advising jpa repository", throwable);
    }
    return object;
  }
}

