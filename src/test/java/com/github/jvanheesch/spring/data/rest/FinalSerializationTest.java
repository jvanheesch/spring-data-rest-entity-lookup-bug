package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.StringOptionalOwner;
import com.github.jvanheesch.spring.data.rest.model.verdict.VerdictRecordOwner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FinalSerializationTest {
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;

    @Test
    void givenAnStringOptionalOwner_whenSerializing_thenEmptyOptionalLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
        ObjectMapper objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
        StringOptionalOwner original = new StringOptionalOwner();

        original.setStringOptional1(Optional.of("abc"));
        original.setStringOptional2(Optional.empty());
        // variables op type Optional<X> should never be null.
        // this code is only here to illustrate jackson's serialization behavior
        original.setStringOptional3(null);

        String json = serialize(objectMapper, original);

        JSONAssert.assertEquals(
                readJsonFromClassPath("StringOptionalOwner.json"),
                json,
                JSONCompareMode.LENIENT
        );

        StringOptionalOwner deserialized = objectMapper.readValue(json, StringOptionalOwner.class);

        assertThat(deserialized.getStringOptional1())
                .contains("abc");
        assertThat(deserialized.getStringOptional2())
                .isEmpty();
        assertThat(deserialized.getStringOptional3())
                .isNull();
    }


    // TODO_JORIS: het is fine dat cases 2 en 3 hetzelfde behavior hebben, denk ik.
    // immers: enkel de eerste komt voor, en die moet leidden tot null in json.
    // 4th mag nt in json staan!
    @Test
    void testSerialization() throws Exception {
        ObjectMapper objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
        String json = serialize(objectMapper, new VerdictRecordOwner());

        JSONAssert.assertEquals(
                readJsonFromClassPath("resource.json"),
                json,
                JSONCompareMode.LENIENT
        );

        VerdictRecordOwner deserialized = objectMapper.readValue(json, VerdictRecordOwner.class);

        System.out.println("todo");
    }

    private String readJsonFromClassPath(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    /**
     * Based on {@link org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#writeInternal(Object, Type, HttpOutputMessage)}.
     */
    private String serialize(ObjectMapper objectMapper, Object object) throws IOException {
        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = objectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter.writeValue(generator, object);

        byte[] bytes = baos.toByteArray();
        return new String(bytes);
    }

    private VerdictRecordOwner getVerdictRecordOwner() {
        return new VerdictRecordOwner();
    }
}
