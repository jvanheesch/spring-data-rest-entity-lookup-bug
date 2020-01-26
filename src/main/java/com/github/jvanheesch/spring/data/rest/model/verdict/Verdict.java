package com.github.jvanheesch.spring.data.rest.model.verdict;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable
public class Verdict {
//    @Id
//    private Long id;
    @JsonProperty("verdict")
    private String string;

    public Verdict(String string) {
        this.string = string;
    }

    public Verdict() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
