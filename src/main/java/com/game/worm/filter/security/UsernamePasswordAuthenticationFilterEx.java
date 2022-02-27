package com.game.worm.filter.security;

import com.game.worm.etc.define.ParameterName;
import com.game.worm.service.exception.ApiException;
import com.game.worm.utils.eErrorInfo;
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

    private boolean checkObtainParam(final String userId, final String userPasswd){
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
        if(!checkObtainParam(userId, userPasswd)){
            throw new ApiException(eErrorInfo.BAD_PARAMETER, false);
        }
        UsernamePasswordAuthenticationToken authReqToken = new UsernamePasswordAuthenticationToken(userId, userPasswd);
        return getAuthenticationManager().authenticate(authReqToken);
    }
}
