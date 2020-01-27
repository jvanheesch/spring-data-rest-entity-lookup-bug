package com.github.jvanheesch.spring.data.rest.model;

import javax.persistence.Embeddable;

@Embeddable
public class StringContainer {
    private String value;

    public StringContainer(String value) {
        this.value = value;
    }

    public StringContainer() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
