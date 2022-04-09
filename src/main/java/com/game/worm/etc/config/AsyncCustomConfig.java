package com.game.worm.etc.config;

import com.game.worm.service.async.handler.AsyncUncaughtExceptionHandlerEx;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncCustomConfig implements AsyncConfigurer {
    private static final int TASK_CORE_POOL_SIZE = 2;
    private static final int TASK_MAX_POOL_SIZE = 4;
    private static final int TASK_QUEUE_CAPACITY = 10;

    @Bean(name = "AsyncThreadPoolTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
        executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
        executor.setQueueCapacity(TASK_QUEUE_CAPACITY);
        executor.setThreadNamePrefix("custom");
        executor.initialize();
        executor.setBeanName("AsyncThreadPoolTaskExecutor");
        return executor;
    }

//    async 메소드에서 던진 exception 잡는 handler 등록
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandlerEx();
    }
}
