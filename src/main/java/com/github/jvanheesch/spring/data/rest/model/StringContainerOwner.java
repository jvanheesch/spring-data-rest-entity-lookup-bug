package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class StringContainerOwner {
    @Id
    private Long id;
    @Transient
    private StringContainer stringContainer1;
    @Transient
    private StringContainer stringContainer2;
    @Transient
    private StringContainer stringContainer3;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public StringContainer getStringContainer2() {
        return stringContainer2;
    }

    public void setStringContainer2(StringContainer stringContainer2) {
        this.stringContainer2 = stringContainer2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public StringContainer getStringContainer3() {
        return stringContainer3;
    }

    public void setStringContainer3(StringContainer stringContainer3) {
        this.stringContainer3 = stringContainer3;
    }
}
