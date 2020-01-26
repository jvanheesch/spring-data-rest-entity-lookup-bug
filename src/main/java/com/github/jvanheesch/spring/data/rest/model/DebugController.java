package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class DebugController {
    private final ObjectMapper objectMapper1;
    private final ObjectMapper objectMapper2 = Jackson2ObjectMapperBuilder.json().build();
    private final ObjectMapper objectMapper3;
    private final VerdictRecordOwnerRepository verdictRecordOwnerRepository;
    private final RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;

    public DebugController(ObjectMapper objectMapper1, VerdictRecordOwnerRepository verdictRecordOwnerRepository, RepositoryRestMvcConfiguration repositoryRestMvcConfiguration) {
        this.objectMapper1 = objectMapper1;
        this.verdictRecordOwnerRepository = verdictRecordOwnerRepository;
        this.repositoryRestMvcConfiguration = repositoryRestMvcConfiguration;
        this.objectMapper3 = repositoryRestMvcConfiguration.objectMapper();
    }

    @GetMapping("/debug")
    public String test() throws IOException {
        VerdictRecordOwner verdictRecordOwner = verdictRecordOwnerRepository.findAll().iterator().next();

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.objectMapper1.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.objectMapper1.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        String string = new String(baos.toByteArray());
        System.out.println(string);
        return string;
    }
}
