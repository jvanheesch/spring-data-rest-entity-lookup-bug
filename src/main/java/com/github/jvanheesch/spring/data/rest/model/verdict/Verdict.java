package com.github.jvanheesch.spring.data.rest.model.verdict;

public class Verdict {
    private String value;

    public Verdict(String value) {
        this.value = value;
    }

    public Verdict() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
