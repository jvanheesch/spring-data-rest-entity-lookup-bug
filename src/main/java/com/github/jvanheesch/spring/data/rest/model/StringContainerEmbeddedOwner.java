package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StringContainerEmbeddedOwner {
    @Id
    private Long id;
    @Embedded
    private StringContainer stringContainer1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public StringContainer getStringContainer1() {
        return stringContainer1;
    }

    public void setStringContainer1(StringContainer stringContainer1) {
        this.stringContainer1 = stringContainer1;
    }
}
