package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SpringRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.withEntityLookup()
                .forValueRepository(
                        AuthorRepository.class,
                        author -> String.valueOf(author.getId()),
                        (authorRepository, id) -> authorRepository.findById(Long.valueOf(id))
                );
    }

    @Bean
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }
}
