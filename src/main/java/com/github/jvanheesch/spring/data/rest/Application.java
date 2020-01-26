package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Author;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordRepository;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRepository;
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
    VerdictRecordRepository verdictRecordRepository;
    @Autowired
    VerdictRepository verdictRepository;

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

        Verdict verdict = new Verdict();
        verdict.setId(1L);
        verdict.setString("Compliant");
        Verdict savedVerdict = verdictRepository.save(verdict);

        VerdictRecord verdictRecord = new VerdictRecord();
        verdictRecord.setId(1L);
        verdictRecord.setVerdict(savedVerdict);

        verdictRecordRepository.save(verdictRecord);
    }
}
