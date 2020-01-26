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
        verdictRecordOwner.setVerdictRecord2(Optional.of(new VerdictRecord()));
        verdictRecordOwner.setVerdictRecord3(Optional.empty());
        verdictRecordOwner.setVerdictRecord4(null);
        String ser = objectMapper.writeValueAsString(verdictRecordOwner);

        System.out.println(ser);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class VerdictRecordOwner {
        private Optional<VerdictRecord> verdictRecord1;
        private Optional<VerdictRecord> verdictRecord2;
        private Optional<VerdictRecord> verdictRecord3;
        private Optional<VerdictRecord> verdictRecord4;

        public Optional<VerdictRecord> getVerdictRecord1() {
            return verdictRecord1;
        }

        public void setVerdictRecord1(Optional<VerdictRecord> verdictRecord1) {
            this.verdictRecord1 = verdictRecord1;
        }

        public Optional<VerdictRecord> getVerdictRecord2() {
            return verdictRecord2;
        }

        public void setVerdictRecord2(Optional<VerdictRecord> verdictRecord2) {
            this.verdictRecord2 = verdictRecord2;
        }

        public Optional<VerdictRecord> getVerdictRecord3() {
            return verdictRecord3;
        }

        public void setVerdictRecord3(Optional<VerdictRecord> verdictRecord3) {
            this.verdictRecord3 = verdictRecord3;
        }

        public Optional<VerdictRecord> getVerdictRecord4() {
            return verdictRecord4;
        }

        public void setVerdictRecord4(Optional<VerdictRecord> verdictRecord4) {
            this.verdictRecord4 = verdictRecord4;
        }
    }
}
