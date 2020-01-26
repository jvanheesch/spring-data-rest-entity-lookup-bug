package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VerdictRecord {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
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
