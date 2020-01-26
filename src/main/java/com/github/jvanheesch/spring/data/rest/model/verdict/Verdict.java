package com.github.jvanheesch.spring.data.rest.model.verdict;

public class Verdict {
    private String string;

    public Verdict(String string) {
        this.string = string;
    }

    public Verdict() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
