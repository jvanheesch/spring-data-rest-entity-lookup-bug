package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import com.github.jvanheesch.spring.data.rest.repo.VerdictRecordOwnerRepository;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
public class DebugController {
    private final ObjectMapper objectMapper;
    private final ObjectMapper dataRestObjectMapper;
    private final VerdictRecordOwnerRepository verdictRecordOwnerRepository;

    public DebugController(
            ObjectMapper objectMapper,
            VerdictRecordOwnerRepository verdictRecordOwnerRepository,
            RepositoryRestMvcConfiguration repositoryRestMvcConfiguration
    ) {
        this.objectMapper = objectMapper;
        this.verdictRecordOwnerRepository = verdictRecordOwnerRepository;
        this.dataRestObjectMapper = repositoryRestMvcConfiguration.halObjectMapper();
    }

    @GetMapping("/debug")
    public String test() throws IOException {
        VerdictRecordOwner verdictRecordOwner = getVerdictRecordOwner();
        // ObjectMapper o = this.dataRestObjectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.dataRestObjectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.dataRestObjectMapper.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        String string = new String(baos.toByteArray());
        System.out.println(string);
        return string;
    }

    private VerdictRecordOwner getVerdictRecordOwner() {
        VerdictRecordOwner verdictRecordOwner = verdictRecordOwnerRepository.findAll().iterator().next();

        Verdict verdict = new Verdict();
        verdict.setString("Compliant");
        VerdictRecord verdictRecord1 = new VerdictRecord();
        verdictRecord1.setId(1L);
        verdictRecord1.setVerdict(verdict);

        VerdictRecord verdictRecord2 = new VerdictRecord();
        verdictRecord2.setId(2L);

        VerdictRecord verdictRecord3 = null;

        verdictRecordOwner.setVerdictRecord1(Optional.ofNullable(verdictRecord1));
        verdictRecordOwner.setVerdictRecord2(Optional.ofNullable(verdictRecord2));
        verdictRecordOwner.setVerdictRecord3(Optional.ofNullable(verdictRecord3));
        verdictRecordOwner.setVerdictRecord4(null);

        return verdictRecordOwner;
    }
}
