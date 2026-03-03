package com.example.Notes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder Encoder () throws Exception{
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain secure(HttpSecurity http,JwtFilter jwtFilter)throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(head->head.frameOptions(frame->frame.disable()))
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/authenticate/**").permitAll()
                        .anyRequest().authenticated()
                );
                //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
