package ru.shintar.aop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shintar.aop.annotation.TrackAsyncTime;
import ru.shintar.aop.annotation.TrackTime;
import ru.shintar.aop.util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SomeService {

    @TrackTime
    public void someSyncMethod() {
        if (Math.random() > 0.9) {
            throw new RuntimeException("SyncMethod some exception");
        }
        leetcode1291(0, Integer.MAX_VALUE);
    }

    @TrackAsyncTime
    public CompletableFuture<Void> someAsyncMethod() {
        if (Math.random() > 0.9) {
            throw new RuntimeException("AsyncMethod some exception");
        }
        return CompletableFuture.runAsync(() -> leetcode1291(0, Integer.MAX_VALUE));
    }

    private void leetcode1291(int low, int high) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            int n = i;
            int next = i + 1;
            while (n <= high && next < 10) {
                n = n * 10 + next;
                if (low <= n && n <= high) {
                    list.add(n);
                }
                ++next;
            }
        }
        list.sort(null);
        ThreadUtils.waitTime((long) (200 * Math.random()));
    }
}
