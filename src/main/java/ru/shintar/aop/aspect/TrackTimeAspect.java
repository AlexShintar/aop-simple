package ru.shintar.aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.shintar.aop.service.TrackTimeDataService;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TrackTimeAspect {
    private final TrackTimeDataService service;

    @Pointcut("@annotation(ru.shintar.aop.annotation.TrackTime) && " +
            "!@annotation(ru.shintar.aop.annotation.TrackAsyncTime)")
    private void trackTimeAspect() {
    }

    @Pointcut("@annotation(ru.shintar.aop.annotation.TrackAsyncTime) && " +
            "!@annotation(ru.shintar.aop.annotation.TrackTime)")
    private void asyncTrackTimeAspect() {
    }

    @Around("trackTimeAspect()")
    public Object trackTime(ProceedingJoinPoint joinPoint) {
        return tracker(joinPoint);
    }

    @Around("asyncTrackTimeAspect()")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> tracker(joinPoint));
        return future.get();
    }

    private Object tracker(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        try {
            Object proceed = joinPoint.proceed();
            service.saveAsync(joinPoint, startTime, "");
            return proceed;
        } catch (Throwable e) {
            service.saveAsync(joinPoint, startTime, e.getMessage());
            return null;
        }
    }
}
