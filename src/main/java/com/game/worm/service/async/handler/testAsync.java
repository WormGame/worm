package com.game.worm.service.async.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class testAsync {
    @Async("AsyncThreadPoolTaskExecutor")
    public void test() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("he");
    }
}
