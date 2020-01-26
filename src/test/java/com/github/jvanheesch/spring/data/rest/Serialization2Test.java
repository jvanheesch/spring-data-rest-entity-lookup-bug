package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Serialization2Test {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerialization() throws Exception {
        VerdictOwner verdictOwner = new VerdictOwner();
        verdictOwner.setVerdict1(Optional.of(new Verdict("v1")));
        String ser = objectMapper.writeValueAsString(verdictOwner);

        System.out.println(ser);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictOwner {
        private Optional<Verdict> verdict1;
        private Optional<Verdict> verdict2;

        public Optional<Verdict> getVerdict1() {
            return verdict1;
        }

        public void setVerdict1(Optional<Verdict> verdict1) {
            this.verdict1 = verdict1;
        }

        public Optional<Verdict> getVerdict2() {
            return verdict2;
        }

        public void setVerdict2(Optional<Verdict> verdict2) {
            this.verdict2 = verdict2;
        }
    }
}
