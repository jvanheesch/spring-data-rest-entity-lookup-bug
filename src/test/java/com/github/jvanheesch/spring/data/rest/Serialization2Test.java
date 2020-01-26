package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
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
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setVerdict1(new VerdictRecord(new Verdict("compliant")));
        // TODO_JORIS: het is fine dat deze 2 cases hetzelfde behavior hebben, denk ik.
        // immers: enkel de eerste komt voor, en die moet leidden tot null in json.
        // 4th mag nt in json staan!
        verdictRecordOwner.setVerdict2(new VerdictRecord(new Verdict()));
        verdictRecordOwner.setVerdict3(new VerdictRecord());
        verdictRecordOwner.setVerdict4(null);
        String ser = objectMapper.writeValueAsString(verdictRecordOwner);

        VerdictRecordOwner deser = objectMapper.readValue(ser, VerdictRecordOwner.class);

        System.out.println(ser);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictRecordOwner {
        private VerdictRecord verdict1;
        private VerdictRecord verdict2;
        private VerdictRecord verdict3;
        private VerdictRecord verdict4;

        public VerdictRecord getVerdict1() {
            return verdict1;
        }

        public void setVerdict1(VerdictRecord verdict1) {
            this.verdict1 = verdict1;
        }

        public VerdictRecord getVerdict2() {
            return verdict2;
        }

        public void setVerdict2(VerdictRecord verdict2) {
            this.verdict2 = verdict2;
        }

        public VerdictRecord getVerdict3() {
            return verdict3;
        }

        public void setVerdict3(VerdictRecord verdict3) {
            this.verdict3 = verdict3;
        }

        public VerdictRecord getVerdict4() {
            return verdict4;
        }

        public void setVerdict4(VerdictRecord verdict4) {
            this.verdict4 = verdict4;
        }
    }
}
