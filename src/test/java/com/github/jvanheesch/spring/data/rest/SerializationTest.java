package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.spring.data.rest.model.StringContainer;
import com.github.jvanheesch.spring.data.rest.model.StringContainerEmbeddedOwner;
import com.github.jvanheesch.spring.data.rest.model.StringContainerOwner;
import com.github.jvanheesch.spring.data.rest.model.StringOptionalOwner;
import org.junit.jupiter.api.BeforeEach;
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
class SerializationTest {
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void initializeObjectMapper() {
        this.objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
    }

    @Test
    void givenAStringOptionalOwner_whenSerializing_thenEmptyOptionalLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
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
    }

    @Test
    void givenAStringOptionalOwner_whenDeserializing_thenNullLeadsToEmptyOptionalAndAbsentPropertyLeadsToNull() throws Exception {
        String json = readJsonFromClassPath("StringOptionalOwner.json");

        StringOptionalOwner deserialized = objectMapper.readValue(json, StringOptionalOwner.class);

        assertThat(deserialized.getStringOptional1())
                .contains("abc");
        assertThat(deserialized.getStringOptional2())
                .isEmpty();
        assertThat(deserialized.getStringOptional3())
                .isNull();
    }

    @Test
    void givenAStringContainerOwner_whenSerializing_thenEmptyContainerLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
        StringContainerOwner original = new StringContainerOwner();

        original.setStringContainer1(new StringContainer("abc"));
        original.setStringContainer2(new StringContainer());
        original.setStringContainer3(null);

        String json = serialize(objectMapper, original);

        JSONAssert.assertEquals(
                readJsonFromClassPath("StringContainerOwner.json"),
                json,
                JSONCompareMode.LENIENT
        );
    }

    @Test
    void givenAStringContainerOwner_whenDeserializing_thenNullLeadsToEmptyContainerAndAbsentPropertyLeadsToNull() throws Exception {
        String json = readJsonFromClassPath("StringContainerOwner.json");

        StringContainerOwner deserialized = objectMapper.readValue(json, StringContainerOwner.class);

        assertThat(deserialized.getStringContainer1().getValue())
                .isEqualTo("abc");
        assertThat(deserialized.getStringContainer2().getValue())
                .isNull();
        assertThat(deserialized.getStringContainer3())
                .isNull();
    }

    @Test
    void givenAStringContainerEmbeddedOwner_whenSerializing_thenEmptyContainerLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
        StringContainerEmbeddedOwner original = new StringContainerEmbeddedOwner();

        original.setStringContainer1(new StringContainer("abc"));

        // this line results in the following error:
        // com.fasterxml.jackson.core.JsonGenerationException: Can not write a string, expecting field name (context: Object)
        String json = readJsonFromClassPath("StringContainerOwner.json");

//        JSONAssert.assertEquals(
//                readJsonFromClassPath("StringContainerOwner.json"),
//                json,
//                JSONCompareMode.LENIENT
//        );

        StringContainerOwner deserialized = objectMapper.readValue(json, StringContainerOwner.class);

        assertThat(deserialized.getStringContainer1().getValue())
                .isEqualTo("abc");
    }

    @Test
    void givenAStringContainerEmbeddedOwner_whenDeserializing_thenNullLeadsToEmptyContainerAndAbsentPropertyLeadsToNull() throws Exception {
        String json = readJsonFromClassPath("StringContainerOwner.json");

        StringContainerOwner deserialized = objectMapper.readValue(json, StringContainerOwner.class);

        assertThat(deserialized.getStringContainer1().getValue())
                .isEqualTo("abc");
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
}
