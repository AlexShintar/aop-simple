package ru.shintar.aop.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shintar.aop.model.TrackTimeData;
import ru.shintar.aop.model.dto.SomeStatsDto;
import ru.shintar.aop.repository.TrackTimeDataRepository;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackTimeDataService {
    private final TrackTimeDataRepository repository;

    @Transactional
    public TrackTimeData save(TrackTimeData trackData) {
        return repository.save(trackData);
    }

    @Async
    @Transactional
    public CompletableFuture<TrackTimeData> saveAsync(ProceedingJoinPoint joinPoint, long startTime, String exception) {

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        long executionTime = System.currentTimeMillis() - startTime;

        TrackTimeData trackData = TrackTimeData
                .builder()
                .methodName(methodName)
                .className(className)
                .executionTime(executionTime)
                .exception(exception)
                .build();

        return CompletableFuture.completedFuture(this.save(trackData));
    }

    public SomeStatsDto getStatsByMethodName(String methodName) {
        List<TrackTimeData> metodList = repository.findAllByMethodNameIgnoreCase(methodName);
        return getStats(metodList);
    }

    private SomeStatsDto getStats(List<TrackTimeData> methods) {

        double avgExecutionTime = methods.stream()
                .mapToLong(TrackTimeData::getExecutionTime)
                .average()
                .orElse(0);

        long totalExecutionTime = methods.stream()
                .mapToLong(TrackTimeData::getExecutionTime)
                .sum();

        HashSet<String> exceptions = methods.stream()
                .map(TrackTimeData::getException)
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toCollection(HashSet::new));

        return SomeStatsDto.builder()
                .executionCount(methods.size())
                .exceptions(exceptions)
                .avgExecutionTime(avgExecutionTime)
                .totalExecutionTime(totalExecutionTime)
                .build();
    }
}
