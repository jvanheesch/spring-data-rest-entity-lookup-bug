package com.github.jvanheesch.spring.data.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.VerdictRecord;
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
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        VerdictRecord verdictRecord = new VerdictRecord();
        verdictRecord.setVerdict(new Verdict("v1"));
        verdictRecordOwner.setVerdictRecord1(Optional.of(verdictRecord));
        String ser = objectMapper.writeValueAsString(verdictRecordOwner);

        System.out.println(ser);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictRecordOwner {
        private Optional<VerdictRecord> verdict1;
        private Optional<VerdictRecord> verdict2;

        public Optional<VerdictRecord> getVerdictRecord1() {
            return verdict1;
        }

        public void setVerdictRecord1(Optional<VerdictRecord> verdict1) {
            this.verdict1 = verdict1;
        }

        public Optional<VerdictRecord> getVerdictRecord2() {
            return verdict2;
        }

        public void setVerdictRecord2(Optional<VerdictRecord> verdict2) {
            this.verdict2 = verdict2;
        }
    }
}
