package com.brodma.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationProperty.class)
public class ApplicationConfig {

    @Autowired
    private ApplicationProperty applicationProperty;

    public int getMaxLoginAttempts() {
        return applicationProperty.getMaxLoginAttempts();
    }

}
