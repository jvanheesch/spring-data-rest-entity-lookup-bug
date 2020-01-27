package com.github.jvanheesch.spring.data.rest.model.verdict;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Verdict {
    @Id
    private Long id;
    private String value;

    public Verdict(String value) {
        this.value = value;
    }

    public Verdict() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
