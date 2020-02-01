package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.EutrDocument;
import com.github.jvanheesch.spring.data.rest.model.WoodComposition;
import com.github.jvanheesch.spring.data.rest.repo.EutrDocumentRepository;
import com.github.jvanheesch.spring.data.rest.repo.WoodCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    EutrDocumentRepository eutrDocumentRepository;
    @Autowired
    WoodCompositionRepository woodCompositionRepository;

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        EutrDocument eutrDocument = new EutrDocument();
        WoodComposition  woodComposition= new WoodComposition();
        woodComposition.setName("wc1");
        eutrDocument.setWoodComposition(woodComposition);
        eutrDocument.setTitle("eutr1");
        this.eutrDocumentRepository.save(eutrDocument);
    }
}
