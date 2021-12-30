package com.game.worm.config;

import com.game.worm.service.UserDetailsServiceImpl;
import com.game.worm.service.security.UserAuthenticationProviderImpl;
import com.game.worm.utils.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final int NONEXPIRE = -1;
    private final UserAuthenticationProviderImpl userAuthenticationProviderImpl;

    @Override
    public void configure(WebSecurity web) {
        final String[] ignoreMatchers = {"/css/**","/js/**","/img/**","/lib/**"};
        web.ignoring().antMatchers(ignoreMatchers);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage(Urls.login)
                .defaultSuccessUrl(Urls.index)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(Urls.logout))
                .logoutSuccessUrl(Urls.index)
                .invalidateHttpSession(true)    // 세션 초기화
                .and().exceptionHandling();
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProviderImpl);
    }
}
