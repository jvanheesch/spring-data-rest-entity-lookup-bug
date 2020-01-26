package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

public final class VerdictRecord {
    private Verdict value;

    public VerdictRecord() {
    }

    public VerdictRecord(Verdict value) {
        this.value = value;
    }

    public Verdict getVerdict() {
        return value;
    }
}
