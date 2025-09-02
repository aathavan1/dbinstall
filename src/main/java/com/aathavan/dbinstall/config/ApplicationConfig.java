package com.aathavan.dbinstall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConnectionConfig.class)
@ComponentScan({"com.aathavan.dbinstall.*"})
public class ApplicationConfig {
}

