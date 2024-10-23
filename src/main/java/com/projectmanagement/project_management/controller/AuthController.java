package com.projectmanagement.project_management.controller;

import java.net.Authenticator;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @GetMapping(value = "/login") 
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("WELCOME HOME CHEATER ☠️");
    }

    @GetMapping(value = "/signup")
    public ResponseEntity<String> signup() {
        return ResponseEntity.ok("BONJOUR");
    }


}