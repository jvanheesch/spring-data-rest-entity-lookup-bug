package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordModule;
import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRepository;
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
        config.withEntityLookup()
                .forValueRepository(
                        VerdictRepository.class,
                        Verdict::getId,
                        (verdictRepository, id) -> verdictRepository.findById(Long.valueOf(id))
                );
        config.disableDefaultExposure();
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
         objectMapper.registerModule(new VerdictRecordModule());
    }

//    /**
//     * https://github.com/spring-projects/spring-data-rest/blob/master/src/main/asciidoc/custom-jackson-deserialization.adoc
//     */
//    @Bean
//    public VerdictRecordModule myJdk8Module() {
//        return new VerdictRecordModule();
//    }
}
