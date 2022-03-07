package com.game.worm.etc.config;

import com.game.worm.filter.security.UsernamePasswordAuthenticationFilterEx;
import com.game.worm.service.security.AuthenticationFailureHandlerImpl;
import com.game.worm.service.security.UserAuthenticationProviderImpl;
import com.game.worm.service.security.UserProviderManagerEx;
import com.game.worm.service.user.UserDetailsServiceImpl;
import com.game.worm.utils.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    public void configure(WebSecurity web) {
        final String[] ignoreMatchers = {"/css/**","/js/**","/img/**","/lib/**"};
        web.ignoring().antMatchers(ignoreMatchers);
    }

    public UsernamePasswordAuthenticationFilterEx getUsernamePasswordAuthenticationFilterEx(){
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(new UserAuthenticationProviderImpl(userDetailsServiceImpl, new BCryptPasswordEncoder()));
        UserProviderManagerEx userAuthenticationManagerEx = new UserProviderManagerEx(authenticationProviders);
        UsernamePasswordAuthenticationFilterEx usernamePasswordAuthenticationFilterEx =  new UsernamePasswordAuthenticationFilterEx(userAuthenticationManagerEx);
        usernamePasswordAuthenticationFilterEx.setAuthenticationFailureHandler(new AuthenticationFailureHandlerImpl());
        return usernamePasswordAuthenticationFilterEx;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String[] matchers = {Urls.signup, Urls.login};
        http.httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(matchers).anonymous()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(Urls.logout))
                .invalidateHttpSession(true)    // 세션 초기화
                .and().formLogin().disable()
        .addFilterAfter(getUsernamePasswordAuthenticationFilterEx(), UsernamePasswordAuthenticationFilter.class);
    }
}
