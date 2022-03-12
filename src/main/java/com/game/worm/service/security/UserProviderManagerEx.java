package com.game.worm.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

@Slf4j
public class UserProviderManagerEx extends ProviderManager {
    public UserProviderManagerEx(List<AuthenticationProvider> providers) {
        super(providers);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<AuthenticationProvider> authenticationProviders =  super.getProviders();
        boolean bProviderFindCheck = false;
        for (AuthenticationProvider provider : authenticationProviders) {
            if(!provider.supports(authentication.getClass())){
                continue;
            }
            bProviderFindCheck = true;
            authentication = provider.authenticate(authentication);
            break;
        }
        if(!bProviderFindCheck){
            log.error("해당하는 AuthenticationProvider가 없습니다.");
            log.debug(authentication.getClass().getName());
        }
        return authentication;
    }

}
