package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class VerdictRecord {
    private Long id;
    @Embedded
    private Verdict verdict;

    public VerdictRecord() {
    }

    public VerdictRecord(Verdict verdict) {
        this.verdict = verdict;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
