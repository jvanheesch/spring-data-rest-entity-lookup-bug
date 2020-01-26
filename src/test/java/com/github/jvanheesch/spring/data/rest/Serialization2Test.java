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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Serialization2Test {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerialization() throws Exception {
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setVerdictRecord1(new VerdictRecord(new Verdict("compliant")));
        // TODO_JORIS: het is fine dat deze 2 cases hetzelfde behavior hebben, denk ik.
        // immers: enkel de eerste komt voor, en die moet leidden tot null in json.
        // 4th mag nt in json staan!
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
    }
}
