package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict verdictRecord1;
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict verdictRecord2;
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict verdictRecord3;
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict verdict;
    // TODO_JORIS lazy
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private VerdictRecord verdictRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<Verdict> getVerdict1() {
        return Optional.ofNullable(verdictRecord1);
    }

    public void setVerdict1(Verdict verdictRecord1) {
        this.verdictRecord1 = verdictRecord1;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<Verdict> getVerdict2() {
        return Optional.ofNullable(verdictRecord2);
    }

    public void setVerdict2(Verdict verdictRecord2) {
        this.verdictRecord2 = verdictRecord2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<Verdict> getVerdict3() {
        return null;
    }

    public void setVerdict3(Verdict verdictRecord3) {
        this.verdictRecord3 = verdictRecord3;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }

    public VerdictRecord getVerdictRecord() {
        return verdictRecord;
    }

    public void setVerdictRecord(VerdictRecord verdictRecord) {
        this.verdictRecord = verdictRecord;
    }
}
