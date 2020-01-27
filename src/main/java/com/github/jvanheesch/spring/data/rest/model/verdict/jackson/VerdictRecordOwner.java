package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Optional;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;
    @Transient
    private Optional<VerdictRecord> verdictRecord1;
    @Transient
    private Optional<VerdictRecord> verdictRecord2;
    @Transient
    private Optional<VerdictRecord> verdictRecord3;
    @Transient
    private Optional<VerdictRecord> verdictRecord4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<VerdictRecord> getVerdictRecord1() {
        return verdictRecord1;
    }

    public void setVerdictRecord1(Optional<VerdictRecord> verdictRecord1) {
        this.verdictRecord1 = verdictRecord1;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<VerdictRecord> getVerdictRecord2() {
        return verdictRecord2;
    }

    public void setVerdictRecord2(Optional<VerdictRecord> verdictRecord2) {
        this.verdictRecord2 = verdictRecord2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<VerdictRecord> getVerdictRecord3() {
        return verdictRecord3;
    }

    public void setVerdictRecord3(Optional<VerdictRecord> verdictRecord3) {
        this.verdictRecord3 = verdictRecord3;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<VerdictRecord> getVerdictRecord4() {
        return verdictRecord4;
    }

    public void setVerdictRecord4(Optional<VerdictRecord> verdictRecord4) {
        this.verdictRecord4 = verdictRecord4;
    }
}
