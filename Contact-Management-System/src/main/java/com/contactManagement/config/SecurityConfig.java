package com.contactManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails adminUser= User
        .withUsername("sanskar")
        .password(passwordEncoder().encode("sanskar"))
        .roles("ADMIN")
        .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(adminUser);
        return inMemoryUserDetailsManager;

    }
    
    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/losung360/contacts/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin();

        return httpSecurity.build();

    }
}
