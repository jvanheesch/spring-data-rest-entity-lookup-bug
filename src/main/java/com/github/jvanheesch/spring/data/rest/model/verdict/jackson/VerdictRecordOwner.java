package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VerdictRecord getVerdictRecord1() {
        return new VerdictRecord("abc");
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VerdictRecord getVerdictRecord2() {
        return new VerdictRecord();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public VerdictRecord getVerdictRecord3() {
        return null;
    }
}
