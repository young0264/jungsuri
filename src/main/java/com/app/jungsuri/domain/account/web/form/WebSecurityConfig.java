package com.app.jungsuri.domain.account.web.form;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new CustomAuthenticationProvider();
//    }

    // 이전코드
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home", "/signup", "/login").permitAll()
////                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated());
//        http
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                        .defaultSuccessUrl("/", true));
//        http
//                .logout((logout) -> logout
//                        .logoutSuccessUrl("/")
//                        .permitAll());
//        return http.build();
//    }

//    new 코드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home", "/signup", "/login")
                        .requestMatchers(
                                Stream
                                .of("/", "/home", "/signup", "/login")
                                .map(AntPathRequestMatcher::antMatcher)
                                .toArray(AntPathRequestMatcher[]::new)
                        )
                        .permitAll()
                        .requestMatchers(
                                Stream
                                .of("/admin/**")
                                .map(AntPathRequestMatcher::antMatcher)
                                .toArray(AntPathRequestMatcher[]::new)
                        ).hasRole("ADMIN")
                        .anyRequest().authenticated());
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


    // PasswordEncoder가 bean으로만 등록되어 잇으면 spring security에서 사용함
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
//                .requestMatchers("/images/**", "/titan/**")
                .requestMatchers(
                        Stream
                        .of("/images/**", "/titan/**")
                        .map(AntPathRequestMatcher::antMatcher)
                        .toArray(AntPathRequestMatcher[]::new))
                ;
    }

}