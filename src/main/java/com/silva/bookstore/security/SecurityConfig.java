package com.silva.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.silva.bookstore.security.UserRoles.ADMIN;
import static com.silva.bookstore.security.UserRoles.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req-> req
                                .requestMatchers("/api/v1/auth/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE, "api/v1/").hasAuthority("WRITE")
//                                .requestMatchers(HttpMethod.POST, "api/v1/").hasAuthority("WRITE")
//                                .requestMatchers(HttpMethod.PUT, "api/v1/").hasAuthority("WRITE")
//                                .requestMatchers(HttpMethod.GET, "api/v1").hasAnyRole(ADMIN.name(), USER.name())
//                                .requestMatchers("api/v1/user/").permitAll()
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
