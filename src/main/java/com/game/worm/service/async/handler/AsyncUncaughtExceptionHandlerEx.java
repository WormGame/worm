package com.game.worm.service.async.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
public class AsyncUncaughtExceptionHandlerEx implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.trace("async exception handler start");
        log.debug("async method name: {}", method.getName());
        log.debug("async method param: {}", params);
        log.debug("async method exception", ex);
        log.trace("async exception handler end");
    }
}
