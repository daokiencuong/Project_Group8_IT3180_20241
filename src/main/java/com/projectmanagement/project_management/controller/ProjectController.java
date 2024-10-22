package com.projectmanagement.project_management.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@ResponseBody 
@RequestMapping("/api/project")
public class ProjectController {
    
    @RolesAllowed("LEADER") 
    @GetMapping(value = "/create") 
    public String createTask() {
        return "Created New Task"; 
    }

    @RolesAllowed({"LEADER", "MEMBER"})
    @GetMapping(value = "/update") 
    public String updateTask() {
        return "Updated the task";
    }

    @GetMapping("/info")
    public Authentication getInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
