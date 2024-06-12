package ru.shintar.aop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SomeStatsDto {

    private long executionCount;
    private HashSet<String> exceptions;
    private double avgExecutionTime;
    private long totalExecutionTime;
}
