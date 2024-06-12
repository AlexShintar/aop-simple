package ru.shintar.aop.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shintar.aop.service.SomeService;

@Component
@RequiredArgsConstructor
public class RandomMethodsRunner {
    private final SomeService someService;

    public void generate() {

        for (int i = 0; i < 100; i++) {
            if (Math.random() < 0.5) {
                someService.someSyncMethod();
            } else {
                someService.someAsyncMethod();
            }
        }
    }
}
