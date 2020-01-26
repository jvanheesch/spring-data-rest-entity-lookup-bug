package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Serialization2Test {
    @Autowired
    private ObjectMapper objectMapper;
    private ObjectMapper objectMapper2 = Jackson2ObjectMapperBuilder.json().build();

    // TODO_JORIS: het is fine dat cases 2 en 3 hetzelfde behavior hebben, denk ik.
    // immers: enkel de eerste komt voor, en die moet leidden tot null in json.
    // 4th mag nt in json staan!
    @Test
    void testSerialization() throws Exception {
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setVerdictRecord1(new VerdictRecord(new Verdict("compliant")));
        verdictRecordOwner.setVerdictRecord2(new VerdictRecord(new Verdict()));
        verdictRecordOwner.setVerdictRecord3(new VerdictRecord());
        verdictRecordOwner.setVerdictRecord4(null);

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.objectMapper2.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.objectMapper2.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes));

        String ser = this.objectMapper2.writeValueAsString(verdictRecordOwner);

        VerdictRecordOwner deser = this.objectMapper2.readValue(ser, VerdictRecordOwner.class);

        System.out.println(ser);
    }
}
