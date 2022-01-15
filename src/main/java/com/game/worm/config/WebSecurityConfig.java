package com.game.worm.config;

import com.game.worm.filter.security.UsernamePasswordAuthenticationFilterEx;
import com.game.worm.service.user.UserDetailsServiceImpl;
import com.game.worm.service.security.UserProviderManagerEx;
import com.game.worm.service.security.UserAuthenticationProviderImpl;
import com.game.worm.utils.BCryptPasswordEncoderEx;
import com.game.worm.utils.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final BCryptPasswordEncoderEx bCryptPasswordEncoderEx;

    @Override
    public void configure(WebSecurity web) {
        final String[] ignoreMatchers = {"/css/**","/js/**","/img/**","/lib/**"};
        web.ignoring().antMatchers(ignoreMatchers);
    }

    @Bean
    UsernamePasswordAuthenticationFilterEx getUsernamePasswordAuthenticationFilterEx(){
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(new UserAuthenticationProviderImpl(userDetailsServiceImpl, bCryptPasswordEncoderEx));
        UserProviderManagerEx userAuthenticationManagerEx = new UserProviderManagerEx(authenticationProviders);
        return new UsernamePasswordAuthenticationFilterEx(userAuthenticationManagerEx);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String[] matchers = {Urls.signup, Urls.login};
        http.httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(matchers).anonymous()
                .antMatchers("/**").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(Urls.logout))
                .logoutSuccessUrl(Urls.index)
                .invalidateHttpSession(true)    // 세션 초기화
                .and().exceptionHandling()
        .and().addFilterAt(getUsernamePasswordAuthenticationFilterEx(), UsernamePasswordAuthenticationFilter.class);
//        security exception만 처리하는 로직 보고 추가하기
         /*.authenticationEntryPoint( new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException, ServletException {
                response.sendRedirect("/login");
            }
        })
                .accessDeniedHandler( new AccessDeniedHandler() {

                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response,
                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/denied");
                    }*/
    }
}
