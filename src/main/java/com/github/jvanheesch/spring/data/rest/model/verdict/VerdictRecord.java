package com.github.jvanheesch.spring.data.rest.model.verdict;

public class VerdictRecord {
    private String value;

    public VerdictRecord(String value) {
        this.value = value;
    }

    public VerdictRecord() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
