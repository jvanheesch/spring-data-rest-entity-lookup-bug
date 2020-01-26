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
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.MyJdk8Module;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
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
    public MyJdk8Module myJdk8Module() {
        return new MyJdk8Module();
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

            serializers.addSerializer(Verdict.class, new VerdictSerializer());
            deserializers.addDeserializer(Verdict.class, new VerdictDeserializer());

            context.addSerializers(serializers);
            context.addDeserializers(deserializers);
        }

        // TODO_JORIS: fix @JsonUnwrapped shit.
        public static class VerdictSerializer extends JsonSerializer<Verdict> {
            @Override
            public void serialize(Verdict value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if ("compliant".equals(value.getString())) {
                    gen.writeNumber(1L);
                } else {
                    gen.writeNull();
                }
            }
        }

        public static class VerdictDeserializer extends StdDeserializer<Verdict> {

            public VerdictDeserializer() {
                super(Verdict.class);
            }

            @Override
            public Verdict deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                JsonNode node = jp.getCodec().readTree(jp);
                Long verdictId = node.longValue();

                if (Long.valueOf(1L).equals(verdictId)) {
                    return new Verdict("Compliant");
                } else {
                    return new Verdict();
                }
            }
        }
    }
}
