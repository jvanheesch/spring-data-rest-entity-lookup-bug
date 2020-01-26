package com.github.jvanheesch.spring.data.rest.model.verdict;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Verdict {
    @JsonProperty("verdict")
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
