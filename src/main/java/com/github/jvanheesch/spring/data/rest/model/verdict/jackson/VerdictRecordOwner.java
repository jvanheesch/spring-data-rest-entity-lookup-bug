package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VerdictRecord getVerdictRecord1() {
        Verdict verdict = new Verdict();
        verdict.setString("compliant");

        VerdictRecord verdictRecord1 = new VerdictRecord();
        verdictRecord1.setId(1L);
        verdictRecord1.setVerdict(verdict);

        return verdictRecord1;
    }

    public VerdictRecord getVerdictRecord2() {
        VerdictRecord verdictRecord2 = new VerdictRecord();
        verdictRecord2.setId(2L);
        return verdictRecord2;
    }

    public VerdictRecord getVerdictRecord3() {
        return new VerdictRecord();
    }

    public VerdictRecord getVerdictRecord4() {
        return null;
    }
}
