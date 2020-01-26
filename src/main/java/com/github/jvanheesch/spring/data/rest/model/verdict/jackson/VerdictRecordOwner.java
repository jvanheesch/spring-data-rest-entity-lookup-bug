package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.*;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;

    private String string1;
    private String string2;
    private String string3;

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

    public Optional<String> getString1() {
        return Optional.ofNullable("abc");
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Optional<String> getString2() {
        return Optional.ofNullable(string2);
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public Optional<String> getString3() {
        return null;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }
}
