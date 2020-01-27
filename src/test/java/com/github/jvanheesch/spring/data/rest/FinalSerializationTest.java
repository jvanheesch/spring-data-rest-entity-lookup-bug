package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.verdict.VerdictRecordOwner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FinalSerializationTest {
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;

    // TODO_JORIS: het is fine dat cases 2 en 3 hetzelfde behavior hebben, denk ik.
    // immers: enkel de eerste komt voor, en die moet leidden tot null in json.
    // 4th mag nt in json staan!
    @Test
    void testSerialization() throws Exception {
        ObjectMapper objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();

        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = objectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter.writeValue(generator, verdictRecordOwner);

        byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes));
    }
}
