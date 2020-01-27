//package com.github.jvanheesch.spring.data.rest.model;
//
//import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
//import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
//import com.github.jvanheesch.spring.data.rest.repo.VerdictRepository;
//
//import java.util.Optional;
//
//public class Util {
//
//    public static VerdictRecordOwner getVerdictRecordOwner() {
//        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
//        verdictRecordOwner.setId(10L);
//
//        Verdict verdict = new Verdict();
//        verdict.setId(1L);
//        verdict.setString("Compliant");
//
//        verdictRecordOwner.setVerdict1(Optional.ofNullable(verdict));
//        verdictRecordOwner.setVerdict2(Optional.ofNullable(null));
//        verdictRecordOwner.setVerdict3(null);
//        verdictRecordOwner.setVerdict(verdict);
//
//        return verdictRecordOwner;
//    }
//
//    public static VerdictRecordOwner getVerdictRecordOwner(VerdictRepository verdictRepository) {
//        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
//        verdictRecordOwner.setId(10L);
//
//        Verdict verdict = verdictRepository.findById(1L).get();
//
//        verdictRecordOwner.setVerdict1(Optional.ofNullable(verdict));
//        verdictRecordOwner.setVerdict2(Optional.ofNullable(null));
//        verdictRecordOwner.setVerdict3(null);
//        verdictRecordOwner.setVerdict(verdict);
//
//        return verdictRecordOwner;
//    }
//}
