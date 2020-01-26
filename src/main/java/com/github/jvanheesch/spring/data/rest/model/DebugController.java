package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class DebugController {
    private final ObjectMapper objectMapper;
    private final VerdictRecordOwnerRepository verdictRecordOwnerRepository;

    public DebugController(ObjectMapper objectMapper, VerdictRecordOwnerRepository verdictRecordOwnerRepository) {
        this.objectMapper = objectMapper;
        this.verdictRecordOwnerRepository = verdictRecordOwnerRepository;
    }

    @GetMapping("/debug")
    public String test() throws IOException {
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
