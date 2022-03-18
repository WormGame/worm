package com.game.worm.filter.security;

import com.game.worm.etc.define.ParameterName;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class UsernamePasswordAuthenticationFilterEx extends UsernamePasswordAuthenticationFilter {
    public UsernamePasswordAuthenticationFilterEx(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    private JsonElement getBody(HttpServletRequest request) throws IOException {
        if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod()))
        {
            return JsonParser.parseString(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        }

        return null;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final JsonElement userInfo;
        try{
            userInfo = getBody(request);
            if(userInfo == null){
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            throw new AuthenticationServiceException("Login 요청 Body 꺼내기 실패(반환 값 NULL)", e);
        } catch (Exception e){
            throw new AuthenticationServiceException("Login 요청 Body 꺼내기 실패", e);
        }
        final String userId;
        final String userPasswd;
        try {
            userId = userInfo.getAsJsonObject().get(ParameterName.USER_ID).getAsString().trim();
            userPasswd = userInfo.getAsJsonObject().get(ParameterName.USER_PASSWD).getAsString().trim();
            if(userId.isEmpty() || userPasswd.isEmpty()){
                throw new AuthenticationServiceException("Login 요청 정보 값이 비어있습니다.");
            }
        }catch (AuthenticationServiceException e){
            throw e;
        } catch (Exception e){
            throw new AuthenticationServiceException("Login 요청 Json 값 꺼내기 실패.", e);
        }
        UsernamePasswordAuthenticationToken authReqToken = new UsernamePasswordAuthenticationToken(userId, userPasswd);
        authReqToken.setAuthenticated(true);
        return getAuthenticationManager().authenticate(authReqToken);
    }
}
