package com.github.jvanheesch.spring.data.rest.model;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;

import java.util.Optional;

public class Util {

    public static VerdictRecordOwner getVerdictRecordOwner() {
        VerdictRecordOwner verdictRecordOwner = new VerdictRecordOwner();
        verdictRecordOwner.setId(10L);

        Verdict verdict = new Verdict();
        verdict.setId(1L);
        verdict.setString("Compliant");
        VerdictRecord verdictRecord1 = new VerdictRecord();
        verdictRecord1.setId(1L);
        verdictRecord1.setVerdict(verdict);

        VerdictRecord verdictRecord2 = new VerdictRecord();
        verdictRecord2.setId(2L);

        VerdictRecord verdictRecord3 = null;

        verdictRecordOwner.setVerdict1(Optional.ofNullable(verdict));
        verdictRecordOwner.setVerdict2(Optional.ofNullable(null));
        verdictRecordOwner.setVerdict3(null);

        return verdictRecordOwner;
    }
}
