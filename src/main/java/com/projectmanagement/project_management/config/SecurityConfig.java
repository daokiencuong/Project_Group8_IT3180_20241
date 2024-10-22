package com.projectmanagement.project_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin( (formLogin) -> formLogin.loginProcessingUrl("/login"));
        
        http.authorizeHttpRequests( (req) -> req
                .requestMatchers("/api/test", "/login").permitAll()
                // .requestMatchers("/api/project/update").hasRole("LEADER")
                .anyRequest().authenticated()            
        );
        
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.csrf(CsrfConfigurer::disable); 
        http.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
        // http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails member = User.withUsername("member")
                .password("{noop}member6666") 
                .authorities("ROLE_MEMBER") 
                .build();
        UserDetails leader = User.withUsername("leader")
                .password("{noop}leader9999")
                .authorities("ROLE_LEADER", "ROLE_MEMBER")
                .build();

        return new InMemoryUserDetailsManager(member, leader);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8000")); //fontend port  
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/api/**", config);  
        source.registerCorsConfiguration("/login", config);
        return source;
    }
}