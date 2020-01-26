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
        VerdictOptionalOwner verdictOptionalOwner = new VerdictOptionalOwner();
        verdictOptionalOwner.setVerdict1(Optional.of(new Verdict("compliant")));
        verdictOptionalOwner.setVerdict2(Optional.of(new Verdict()));
        verdictOptionalOwner.setVerdict3(Optional.empty());
        verdictOptionalOwner.setVerdict4(null);
        String ser = objectMapper.writeValueAsString(verdictOptionalOwner);

        System.out.println(ser);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictOptionalOwner {
        private Optional<Verdict> verdict1;
        private Optional<Verdict> verdict2;
        private Optional<Verdict> verdict3;
        private Optional<Verdict> verdict4;

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

        public Optional<Verdict> getVerdict3() {
            return verdict3;
        }

        public void setVerdict3(Optional<Verdict> verdict3) {
            this.verdict3 = verdict3;
        }

        public Optional<Verdict> getVerdict4() {
            return verdict4;
        }

        public void setVerdict4(Optional<Verdict> verdict4) {
            this.verdict4 = verdict4;
        }
    }
}
