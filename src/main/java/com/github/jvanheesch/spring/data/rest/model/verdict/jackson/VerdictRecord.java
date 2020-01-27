package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import javax.persistence.Embeddable;

@Embeddable
public class VerdictRecord {
//    private Long id;
    private String value;

    public VerdictRecord(String value) {
        this.value = value;
    }

    public VerdictRecord() {
    }

//    public Long getId() {
//        return id;
//    }
//
    public void setId(Long id) {
//        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
