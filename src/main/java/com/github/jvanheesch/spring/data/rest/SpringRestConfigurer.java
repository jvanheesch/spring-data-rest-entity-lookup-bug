package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
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
}
