package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.*;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict verdict;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord verdictRecord1;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord verdictRecord2;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VerdictRecord verdictRecord3;

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

    public VerdictRecord getVerdictRecord1() {
        return verdictRecord1;
    }

    public void setVerdictRecord1(VerdictRecord verdictRecord1) {
        this.verdictRecord1 = verdictRecord1;
    }

    public VerdictRecord getVerdictRecord2() {
        return verdictRecord2;
    }

    public void setVerdictRecord2(VerdictRecord verdictRecord2) {
        this.verdictRecord2 = verdictRecord2;
    }

    public VerdictRecord getVerdictRecord3() {
        return verdictRecord3;
    }

    public void setVerdictRecord3(VerdictRecord verdictRecord3) {
        this.verdictRecord3 = verdictRecord3;
    }
}
