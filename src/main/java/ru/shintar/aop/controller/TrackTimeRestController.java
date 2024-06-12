package ru.shintar.aop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shintar.aop.generator.RandomMethodsRunner;
import ru.shintar.aop.model.dto.SomeStatsDto;

import ru.shintar.aop.service.TrackTimeDataService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "TrackTime Data API", description = "API for TrackTime data")
public class TrackTimeRestController {

    private final RandomMethodsRunner randomMethodsRunner;
    private final TrackTimeDataService trackTimeDataService;

    @GetMapping("/generateMethods")
    @Operation(summary = "Generate methods for test", description = "Generate of 100 methods for testing", operationId = "generateMethods")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public ResponseEntity<String> generateMethods() {
        randomMethodsRunner.generate();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/stat/{methodName}")
    @Operation(summary = "Get statistic by methods name", description = "Get statistic by methods name", operationId = "getStatsByMethodName")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    public SomeStatsDto getStatsByMethodName(
            @Parameter(description = "Method name", required = true) @PathVariable String methodName
    ) {
        return trackTimeDataService.getStatsByMethodName(methodName);
    }
}
