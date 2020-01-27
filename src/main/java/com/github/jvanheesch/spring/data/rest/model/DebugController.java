package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
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
    private ObjectMapper objectMapper;
    private final VerdictRecordOwnerRepository verdictRecordOwnerRepository;

    public DebugController(VerdictRecordOwnerRepository verdictRecordOwnerRepository, RepositoryRestMvcConfiguration repositoryRestMvcConfiguration) {
        this.verdictRecordOwnerRepository = verdictRecordOwnerRepository;
        this.objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
    }

    @GetMapping("/debug")
    public String test() throws IOException {
        // this.objectMapper = this.objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        VerdictRecordOwner verdictRecordOwner = verdictRecordOwnerRepository.findAll().iterator().next();

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.objectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.objectMapper.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        String string = new String(baos.toByteArray());
        System.out.println(string);
        return string;
    }
}
