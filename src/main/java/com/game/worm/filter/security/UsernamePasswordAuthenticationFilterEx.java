package com.game.worm.filter.security;

import com.game.worm.etc.define.Messages;
import com.game.worm.etc.define.ParameterName;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsernamePasswordAuthenticationFilterEx extends UsernamePasswordAuthenticationFilter {
    public UsernamePasswordAuthenticationFilterEx(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private boolean checkObtainParma(final String userId, final String userPasswd){
        if(userId == null || userPasswd == null){
            return false;
        }
        if(userPasswd.isEmpty() || userId.isEmpty()){
            return false;
        }

        return true;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String userId = request.getParameter(ParameterName.USER_ID);
        final String userPasswd = request.getParameter(ParameterName.USER_PASSWD);
        if(!checkObtainParma(userId, userPasswd)){
            throw new AssertionError(Messages.BAD_PARAM);
        }
        UsernamePasswordAuthenticationToken authReqToken = new UsernamePasswordAuthenticationToken(userId, userPasswd);
        return getAuthenticationManager().authenticate(authReqToken);
    }
}
