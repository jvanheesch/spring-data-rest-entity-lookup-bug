package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SerializationTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerialization() throws Exception {
        VerdictOwner verdictOwner = new VerdictOwner();
        verdictOwner.setVerdict1(Optional.of("v1"));
        String ser = objectMapper.writeValueAsString(verdictOwner);

        assertThat(ser)
                .isEqualTo("{\"verdict1\":\"v1\"}");

        VerdictOwner deser = objectMapper.readValue(ser, VerdictOwner.class);
        assertThat(deser.getVerdict1())
                .contains("v1");
        assertThat(deser.getVerdict2())
                .isNull();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictOwner {
        private Optional<String> verdict1;
        private Optional<String> verdict2;

        public Optional<String> getVerdict1() {
            return verdict1;
        }

        public void setVerdict1(Optional<String> verdict1) {
            this.verdict1 = verdict1;
        }

        public Optional<String> getVerdict2() {
            return verdict2;
        }

        public void setVerdict2(Optional<String> verdict2) {
            this.verdict2 = verdict2;
        }
    }
}
