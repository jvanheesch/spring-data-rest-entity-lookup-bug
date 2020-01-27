//package com.github.jvanheesch.spring.data.rest;
//
//import com.fasterxml.jackson.core.JsonEncoding;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
//import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
//import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.io.ByteArrayOutputStream;
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class Serialization3Test {
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testSerialization() throws Exception {
//        this.objectMapper = new ObjectMapper().registerModule(new Jdk8Module());
//
//        VerdictRecordOwner verdictRecordOwner = this.getVerdictRecordOwner();
//
//        JsonEncoding encoding = JsonEncoding.UTF8;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        JsonGenerator generator = this.objectMapper.getFactory().createGenerator(baos, encoding);
//
//        ObjectWriter objectWriter = this.objectMapper.writer();
//        objectWriter.writeValue(generator, verdictRecordOwner);
//
//        byte[] bytes = baos.toByteArray();
//        System.out.println(new String(bytes));
//    }
//
//    private VerdictRecordOwner getVerdictRecordOwner() {
//        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
//        verdictRecordOwner.setId(3L);
//
//        Verdict verdict = new Verdict();
//        verdict.setString("Compliant");
//        VerdictRecord verdictRecord1 = new VerdictRecord();
//        verdictRecord1.setId(1L);
//        verdictRecord1.setVerdict(verdict);
//
//        VerdictRecord verdictRecord2 = new VerdictRecord();
//        verdictRecord2.setId(2L);
//
//        VerdictRecord verdictRecord3 = null;
//
//        verdictRecordOwner.setVerdictRecord1(Optional.ofNullable(verdictRecord1));
//        verdictRecordOwner.setVerdictRecord2(Optional.ofNullable(verdictRecord2));
//        verdictRecordOwner.setVerdictRecord3(Optional.ofNullable(verdictRecord3));
//        verdictRecordOwner.setVerdictRecord4(null);
//
//        return verdictRecordOwner;
//    }
//}
