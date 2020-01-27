package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jvanheesch.spring.data.rest.jackson.StringContainerModule;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SpringRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.disableDefaultExposure();
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new StringContainerModule());
    }
}
