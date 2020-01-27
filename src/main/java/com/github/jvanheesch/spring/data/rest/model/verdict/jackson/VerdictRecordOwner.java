package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;
    @Transient
    private VerdictRecord verdictRecord1;
//    @Embedded
//    private VerdictRecord verdictRecord2;
//    @Embedded
//    private VerdictRecord verdictRecord3;

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

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public VerdictRecord getVerdictRecord2() {
//        return verdictRecord2;
//    }
//
//    public void setVerdictRecord2(VerdictRecord verdictRecord2) {
//        this.verdictRecord2 = verdictRecord2;
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public VerdictRecord getVerdictRecord3() {
//        return verdictRecord3;
//    }
//
//    public void setVerdictRecord3(VerdictRecord verdictRecord3) {
//        this.verdictRecord3 = verdictRecord3;
//    }
}
