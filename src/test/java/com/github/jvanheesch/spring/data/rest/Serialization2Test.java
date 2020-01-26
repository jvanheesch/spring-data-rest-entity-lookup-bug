package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Serialization2Test {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerialization() throws Exception {
        VerdictOptionalOwner verdictOptionalOwner = new VerdictOptionalOwner();
        verdictOptionalOwner.setVerdict1(new Optional(new Verdict("compliant")));
        // TODO_JORIS: het is fine dat deze 1 cases hetzelfde behavior hebben, denk ik.
        // immers: deze case komt niet voor, ofwel zit er een legit verdit in, ofwel geen!
        verdictOptionalOwner.setVerdict2(new Optional(new Verdict()));
        verdictOptionalOwner.setVerdict3(new Optional());
        verdictOptionalOwner.setVerdict4(null);
        String ser = objectMapper.writeValueAsString(verdictOptionalOwner);

        VerdictOptionalOwner deser = objectMapper.readValue(ser, VerdictOptionalOwner.class);

        System.out.println(ser);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictOptionalOwner {
        private Optional verdict1;
        private Optional verdict2;
        private Optional verdict3;
        private Optional verdict4;

        public Optional getVerdict1() {
            return verdict1;
        }

        public void setVerdict1(Optional verdict1) {
            this.verdict1 = verdict1;
        }

        public Optional getVerdict2() {
            return verdict2;
        }

        public void setVerdict2(Optional verdict2) {
            this.verdict2 = verdict2;
        }

        public Optional getVerdict3() {
            return verdict3;
        }

        public void setVerdict3(Optional verdict3) {
            this.verdict3 = verdict3;
        }

        public Optional getVerdict4() {
            return verdict4;
        }

        public void setVerdict4(Optional verdict4) {
            this.verdict4 = verdict4;
        }
    }
}
