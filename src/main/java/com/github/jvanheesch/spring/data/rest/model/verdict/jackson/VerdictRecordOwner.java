package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Optional;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;
    @Transient
    private Optional<Verdict> verdictRecord1;
    @Transient
    private Optional<Verdict> verdictRecord2;
    @Transient
    private Optional<Verdict> verdictRecord3;
    @Transient
    private Verdict verdict;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<Verdict> getVerdict1() {
        return verdictRecord1;
    }

    public void setVerdict1(Optional<Verdict> verdictRecord1) {
        this.verdictRecord1 = verdictRecord1;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<Verdict> getVerdict2() {
        return verdictRecord2;
    }

    public void setVerdict2(Optional<Verdict> verdictRecord2) {
        this.verdictRecord2 = verdictRecord2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<Verdict> getVerdict3() {
        return verdictRecord3;
    }

    public void setVerdict3(Optional<Verdict> verdictRecord3) {
        this.verdictRecord3 = verdictRecord3;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
