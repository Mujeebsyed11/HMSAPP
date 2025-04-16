package com.hmsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);// this line filters only the url's which comes with the token

        http.authorizeHttpRequests().anyRequest().permitAll();

//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/user/sign-up", "/api/v1/auth/owner/sign-up", "/api/v1/auth/login")
//                .permitAll()
//                .requestMatchers("/api/v1/property", "/api/v1/property/addProperty", "/api/v1/property/deleteProperty", "/api/v1/property/updateProperty")
//                .hasAnyRole("OWNER", "ADMIN")
//                .requestMatchers("/api/v1/auth/blog/sign-up")
//                .hasAnyRole("ADMIN")
//                .anyRequest().authenticated();
        return http.build();
    }

}
