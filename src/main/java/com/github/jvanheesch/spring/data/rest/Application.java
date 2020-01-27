package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.verdict.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    @Autowired
    VerdictRecordOwnerRepository verdictRecordOwnerRepository;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setId(1L);
        verdictRecordOwnerRepository.save(verdictRecordOwner);
    }
}
