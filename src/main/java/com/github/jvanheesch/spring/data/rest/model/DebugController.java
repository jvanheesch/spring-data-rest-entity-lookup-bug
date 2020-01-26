package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class DebugController {
    private final ObjectMapper objectMapper;

    public DebugController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/debug")
    public String test() throws IOException {
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setVerdictRecord1(new VerdictRecord(new Verdict("compliant")));
        verdictRecordOwner.setVerdictRecord2(new VerdictRecord(new Verdict()));
        verdictRecordOwner.setVerdictRecord3(new VerdictRecord());
        verdictRecordOwner.setVerdictRecord4(null);

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.objectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.objectMapper.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes));

        String ser = objectMapper.writeValueAsString(verdictRecordOwner);

        VerdictRecordOwner deser = objectMapper.readValue(ser, VerdictRecordOwner.class);

        System.out.println(ser);
        return "abc";
    }
}
