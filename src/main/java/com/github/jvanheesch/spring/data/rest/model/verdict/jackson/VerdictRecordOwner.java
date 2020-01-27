package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.jvanheesch.spring.data.rest.model.verdict.VerdictRecord;

import javax.persistence.*;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VerdictRecord getVerdictRecord1() {
        return verdictRecord1;
    }

    public void setVerdictRecord1(VerdictRecord verdictRecord1) {
        this.verdictRecord1 = verdictRecord1;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VerdictRecord getVerdictRecord2() {
        return verdictRecord2;
    }

    public void setVerdictRecord2(VerdictRecord verdictRecord2) {
        this.verdictRecord2 = verdictRecord2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VerdictRecord getVerdictRecord3() {
        return verdictRecord3;
    }

    public void setVerdictRecord3(VerdictRecord verdictRecord3) {
        this.verdictRecord3 = verdictRecord3;
    }

    //    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public VerdictRecord getVerdictRecord1() {
//        return new VerdictRecord(new Verdict("abc"));
//    }
//
//    public void setVerdictRecord1(VerdictRecord verdictRecord) {
//        this.verdictRecord1 = verdictRecord;
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public VerdictRecord getVerdictRecord2() {
//        return new VerdictRecord();
//    }
//
//    public void setVerdictRecord2(VerdictRecord verdictRecord2) {
//        this.verdictRecord2 = verdictRecord2;
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public VerdictRecord getVerdictRecord3() {
//        return null;
//    }
//
//    public void setVerdictRecord3(VerdictRecord verdictRecord3) {
//        this.verdictRecord3 = verdictRecord3;
//    }
}
