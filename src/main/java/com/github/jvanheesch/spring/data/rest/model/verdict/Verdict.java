package com.github.jvanheesch.spring.data.rest.model.verdict;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Verdict {
    @Id
    private Long id;
    private String string;

    public Verdict(String string) {
        this.string = string;
    }

    public Verdict() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
