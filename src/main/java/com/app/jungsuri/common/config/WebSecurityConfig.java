package com.app.jungsuri.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    (requests) -> requests
                    .requestMatchers(
                            Stream
                            .of("/", "/error", "/home", "/signup", "/login", "/check-email-token")
                            .map(AntPathRequestMatcher::antMatcher)
                            .toArray(AntPathRequestMatcher[]::new))
                            .permitAll()
            );
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers(
                                        Stream.of("/settings/admin")
                                                .map(AntPathRequestMatcher::antMatcher)
                                                .toArray(AntPathRequestMatcher[]::new)
                                ).hasRole("ADMIN")
                                .anyRequest().authenticated()
                );

        http
            .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/", true));
        http
            .logout((logout) -> logout
                    .logoutSuccessUrl("/")
                    .permitAll());
        return http.build();
    }


    // PasswordEncoder가 bean으로만 등록되어 잇으면 spring security에서 사용함.
    // PasswordEncoder를 직접 생성하는게 아닌 createDelegatingPasswordEncoder에 위임
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //static 파일 경로 접근
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers(
                        Stream
                        .of("/images/**", "/titan/**")
                        .map(AntPathRequestMatcher::antMatcher)
                        .toArray(AntPathRequestMatcher[]::new));
    }

}