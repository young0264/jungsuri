package com.app.jungsuri.domain.account.web.form;

import com.app.jungsuri.domain.account.persistence.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/signup", "/login").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated());
        http
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true));
        http
                .logout((logout) -> logout
//                        .logoutSuccessUrl("/")
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
                .requestMatchers("/images/**", "/titan/**")
                ;
    }

}