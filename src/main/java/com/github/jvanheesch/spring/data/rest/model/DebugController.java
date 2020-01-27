package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRepository;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class DebugController {
    private final ObjectMapper objectMapper;
    private final ObjectMapper dataRestObjectMapper;
    private final VerdictRecordOwnerRepository verdictRecordOwnerRepository;
    private final VerdictRepository verdictRepository;

    public DebugController(
            ObjectMapper objectMapper,
            VerdictRecordOwnerRepository verdictRecordOwnerRepository,
            RepositoryRestMvcConfiguration repositoryRestMvcConfiguration,
            VerdictRepository verdictRepository) {
        this.objectMapper = objectMapper;
        this.verdictRecordOwnerRepository = verdictRecordOwnerRepository;
        this.dataRestObjectMapper = repositoryRestMvcConfiguration.halObjectMapper();
        this.verdictRepository = verdictRepository;
    }

    @GetMapping("/debug")
    public String test() throws IOException {
        VerdictRecordOwner verdictRecordOwner = Util.getVerdictRecordOwner(verdictRepository);

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.dataRestObjectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.dataRestObjectMapper.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        String string = new String(baos.toByteArray());
        System.out.println(string);
        return string;
    }
}
