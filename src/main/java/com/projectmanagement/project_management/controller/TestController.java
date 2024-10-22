package com.projectmanagement.project_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping(value = "/test")
    public @ResponseBody String test() {
        return "OK";
    }
}