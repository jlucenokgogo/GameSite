package game.example.server.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component("LogAspect")
public class LogAspect {

    @Before("execution(* game.example.server.service.impl.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* game.example.server.service.impl.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* game.example.server.service.impl.*.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        log.info("After returning method: " + joinPoint.getSignature().getName() + " with result: ");
    }

    @AfterThrowing(pointcut = "execution(* game.example.server.service.impl.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("Exception in method: " + joinPoint.getSignature().getName() + " with message: " + ex.getMessage());
    }
}
