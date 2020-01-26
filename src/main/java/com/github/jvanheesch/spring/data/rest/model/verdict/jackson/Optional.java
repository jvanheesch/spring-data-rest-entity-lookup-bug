package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

public final class Optional {
    private Verdict value;

    public Optional() {
    }

    public Optional(Verdict value) {
        this.value = value;
    }

    public Verdict getVerdict() {
        return value;
    }
}
