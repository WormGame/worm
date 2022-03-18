package com.game.worm.etc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
@Slf4j
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final int LOGIN_EXPIRE = -1;
    private static final int NORMAL_EXPIRE = 60 * 15;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            se.getSession().setMaxInactiveInterval(LOGIN_EXPIRE);
        } else{
            se.getSession().setMaxInactiveInterval(NORMAL_EXPIRE);
        }
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
