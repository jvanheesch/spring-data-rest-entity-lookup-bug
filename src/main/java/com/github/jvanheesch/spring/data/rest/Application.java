package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Application {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

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
}
