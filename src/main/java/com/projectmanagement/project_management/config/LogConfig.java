package com.projectmanagement.project_management.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.io.File;

@Configuration
public class LogConfig {

    private static final Logger logger = LoggerFactory.getLogger("startup-reset");

    @PostConstruct
    public void init() {
        logger.info("Application restarted, clearing old logs.");
    }

    @PreConstruct
    public void cleanLog() {
        File logFile = new File("logs/application.log");
        if (logFile.exists()) {
            logFile.delete(); // Xóa file log cũ
        }
    }
}
