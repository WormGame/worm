package com.game.worm.service.security;

import com.game.worm.etc.define.ResponseFormat;
import com.game.worm.etc.define.eErrorInfo;
import com.game.worm.utils.CommonUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        if(exception.getCause() != null) {
            log.error(CommonUtils.getStackTraceElements(exception.getCause()));
        }
        if(exception.getMessage() != null && !exception.getMessage().trim().isEmpty()){
            log.error(exception.getMessage());
        }

        response.setContentType("application/json");
        PrintWriter resOut;
        try {
            resOut = response.getWriter();
            if(resOut == null){
                throw new IOException();
            }
        } catch (IOException e) {
            log.error(CommonUtils.getStackTraceElements(e));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return;
        }

        Gson gson = new Gson();
        String resResult = gson.toJson(new ResponseFormat(
                eErrorInfo.FAIL_LOGIN.getMessage(),
                false,
                null));
        resOut.print(resResult);
        resOut.flush();
    }
}
