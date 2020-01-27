package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Author;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    VerdictRecordOwnerRepository verdictRecordOwnerRepository;

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
        Author author = new Author();
        author.setName("Oliver");
        authorRepository.save(author);

        VerdictRecord filledInVerdictRecord = new VerdictRecord();
        filledInVerdictRecord.setId(1L);
        filledInVerdictRecord.setValue("compliant");
        VerdictRecord blankVerdictRecord = new VerdictRecord();
        blankVerdictRecord.setId(2L);
        blankVerdictRecord.setValue(null);
        VerdictRecord nullVerdictRecord = null;

        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setId(1L);
        verdictRecordOwner.setVerdictRecord1(filledInVerdictRecord);
//        verdictRecordOwner.setVerdictRecord2(blankVerdictRecord);
//        verdictRecordOwner.setVerdictRecord3(nullVerdictRecord);

        verdictRecordOwnerRepository.save(verdictRecordOwner);
    }
}
