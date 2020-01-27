package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Author;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
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
    VerdictRepository verdictRepository;
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

        Verdict compliant = new Verdict();
        compliant.setId(1L);
        compliant.setString("Compliant");
        verdictRepository.save(compliant);
        Verdict nonCompliant = new Verdict();
        nonCompliant.setId(1L);
        nonCompliant.setString("Non-compliant");
        verdictRepository.save(nonCompliant);

        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setId(1L);
        verdictRecordOwner.setVerdict(compliant);
        verdictRecordOwner.setVerdict1(compliant);
        verdictRecordOwner.setVerdict1(nonCompliant);
        verdictRecordOwnerRepository.save(verdictRecordOwner);

//        VerdictRecord verdictRecord1 = new VerdictRecord();
//        verdictRecord1.setId(1L);
//        verdictRecord1.setVerdict(verdict);
//
//        VerdictRecord verdictRecord2 = new VerdictRecord();
//        verdictRecord2.setId(2L);
//
//        VerdictRecord verdictRecord3 = null;
//
//        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
//        verdictRecordOwner.setId(1L);
//        verdictRecordOwner.setVerdictRecord1(Optional.ofNullable(verdictRecord1));
//        verdictRecordOwner.setVerdictRecord2(Optional.ofNullable(verdictRecord2));
//        verdictRecordOwner.setVerdictRecord3(Optional.ofNullable(verdictRecord3));
//        verdictRecordOwner.setVerdictRecord4(null);
//
//        verdictRecordOwnerRepository.save(verdictRecordOwner);
    }


//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public Optional<VerdictRecord> getVerdictRecord1() {
//        Verdict verdict = new Verdict();
//        verdict.setString("compliant");
//
//        VerdictRecord verdictRecord1 = new VerdictRecord();
//        verdictRecord1.setId(1L);
//        verdictRecord1.setVerdict(verdict);
//
//        return Optional.ofNullable(verdictRecord1);
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public Optional<VerdictRecord> getVerdictRecord2() {
//        VerdictRecord verdictRecord2 = new VerdictRecord();
//        verdictRecord2.setId(2L);
//        return Optional.ofNullable(verdictRecord2);
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public Optional<VerdictRecord> getVerdictRecord3() {
//        return Optional.empty();
//    }
//
//    // TODO_JORIS: evil ofc
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public Optional<VerdictRecord> getVerdictRecord4() {
//        return null;
//    }
}
