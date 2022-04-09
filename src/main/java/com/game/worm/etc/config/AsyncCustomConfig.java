package com.game.worm.etc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

@Configuration
public class AsyncCustomConfig implements AsyncConfigurer {
    private static int TASK_CORE_POOL_SIZE = 2;
    private static int TASK_MAX_POOL_SIZE = 4;
    private static int TASK_QUEUE_CAPACITY = 10;
    private static String BEAN_NAME = "executorSample";

    @Resource(name = "AsycnThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executorSample;

    @Bean(name = "AsycnThreadPoolTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
        executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
        executor.setQueueCapacity(TASK_QUEUE_CAPACITY);
        executor.setBeanName(BEAN_NAME);
        executor.initialize();
        return executor;
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler();
    }
}
