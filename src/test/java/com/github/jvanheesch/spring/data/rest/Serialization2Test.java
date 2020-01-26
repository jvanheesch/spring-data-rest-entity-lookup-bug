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
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Serialization2Test {
    private ObjectMapper objectMapper1;
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;

    // TODO_JORIS: het is fine dat cases 2 en 3 hetzelfde behavior hebben, denk ik.
    // immers: enkel de eerste komt voor, en die moet leidden tot null in json.
    // 4th mag nt in json staan!
    @Test
    void testSerialization() throws Exception {
        this.objectMapper1 = repositoryRestMvcConfiguration.halObjectMapper();
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = this.objectMapper1.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = this.objectMapper1.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes));
    }
}
