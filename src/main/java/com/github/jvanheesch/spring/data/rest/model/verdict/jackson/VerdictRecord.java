package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

public class VerdictRecord {
    private Verdict value;

    public VerdictRecord(Verdict value) {
        this.value = value;
    }

    public VerdictRecord() {
    }

    public Verdict getValue() {
        return value;
    }

    public void setValue(Verdict value) {
        this.value = value;
    }
}
