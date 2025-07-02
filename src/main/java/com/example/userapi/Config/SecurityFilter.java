package com.example.userapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityFilter {


    private final JwtAuthFilter jwtAuthFilter;

    public SecurityFilter(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }
    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		        .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
		        .authorizeHttpRequests(auth -> auth
		            .requestMatchers("/api/user/register", "/api/auth/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
		            .anyRequest().authenticated()
		        )
		        .formLogin(login -> login.disable())  // disable default login page
		        .httpBasic(httpBasic -> httpBasic.disable()) // disable basic auth

		        // ✅ correct chaining
		        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

		        .build(); // ✅ chain ends here
    }
}
