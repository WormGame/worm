package com.game.worm.service.security;

import com.game.worm.etc.define.Messages;
import com.game.worm.service.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RequiredArgsConstructor
public class UserAuthenticationProviderImpl implements AuthenticationProvider {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private boolean checkedParam(Authentication authentication){
        if(!(authentication.getCredentials() instanceof String)){
            return false;
        }
        if(!(authentication.getPrincipal() instanceof String)){
            return false;
        }
        return true;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(!checkedParam(authentication)){
            throw new TypeNotPresentException("String", null);
        }
        String passwordInClient = (String) authentication.getCredentials();
        String userId = (String) authentication.getPrincipal();
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userId);

        final String passwordInDB = userDetails.getPassword();
        if(!bCryptPasswordEncoder.matches(passwordInClient, passwordInDB)){
            throw new BadCredentialsException(Messages.WRONG_PASSWD);
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
