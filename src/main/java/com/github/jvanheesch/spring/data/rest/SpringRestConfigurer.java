package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.repo.AuthorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.withEntityLookup()
                .forValueRepository(
                        AuthorRepository.class,
                        author -> String.valueOf(author.getId()),
                        (authorRepository, id) -> authorRepository.findById(Long.valueOf(id))
                );
        config.disableDefaultExposure();
    }

    @Bean
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }


    /**
     * https://github.com/spring-projects/spring-data-rest/blob/master/src/main/asciidoc/custom-jackson-deserialization.adoc
     */
    @Bean
    public SimpleModule customSerializationOptionsModule() {
        return new CustomSerializationOptionsModule();
    }

    public static class CustomSerializationOptionsModule extends SimpleModule {
        @Override
        public void setupModule(SetupContext context) {
            SimpleSerializers serializers = new SimpleSerializers();
            SimpleDeserializers deserializers = new SimpleDeserializers();

            serializers.addSerializer(VerdictRecord.class, new VerdictRecordSerializer());
            deserializers.addDeserializer(VerdictRecord.class, new VerdictRecordDeserializer());

            context.addSerializers(serializers);
            context.addDeserializers(deserializers);
        }

        // TODO_JORIS: fix @JsonUnwrapped shit.
        public static class VerdictRecordSerializer extends JsonSerializer<VerdictRecord> {
            @Override
            public void serialize(VerdictRecord value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                Verdict verdict = value.getVerdict();
                if (verdict != null) {
                    gen.writeNumber(1L);
                } else {
                    gen.writeNull();
                }
            }
        }

        public static class VerdictRecordDeserializer extends StdDeserializer<VerdictRecord> {

            public VerdictRecordDeserializer() {
                super(VerdictRecord.class);
            }

            @Override
            public VerdictRecord deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                JsonNode node = jp.getCodec().readTree(jp);
                Long verdictId = node.longValue();

                VerdictRecord verdict = new VerdictRecord();
                return verdict;
            }
        }
    }
}
