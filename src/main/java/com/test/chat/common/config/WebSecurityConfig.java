package com.test.chat.common.config;

import com.test.chat.common.code.RoleCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Value("${front.url}")
    private String frontUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect(frontUrl + "list");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect(frontUrl);
                })
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect(frontUrl);
                })
                .and()
                .authorizeRequests()
                .antMatchers("/chat/**").hasRole(RoleCode.ROLE_USER.getValue())
                .anyRequest().permitAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
