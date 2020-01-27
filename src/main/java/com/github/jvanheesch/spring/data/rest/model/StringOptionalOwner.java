package com.github.jvanheesch.spring.data.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Optional;

@Entity
public class StringOptionalOwner {
    @Id
    private Long id;
    @Transient
    private Optional<String> stringOptional1;
    @Transient
    private Optional<String> stringOptional2;
    @Transient
    private Optional<String> stringOptional3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<String> getStringOptional1() {
        return stringOptional1;
    }

    public void setStringOptional1(Optional<String> stringOptional1) {
        this.stringOptional1 = stringOptional1;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<String> getStringOptional2() {
        return stringOptional2;
    }

    public void setStringOptional2(Optional<String> stringOptional2) {
        this.stringOptional2 = stringOptional2;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<String> getStringOptional3() {
        return stringOptional3;
    }

    public void setStringOptional3(Optional<String> stringOptional3) {
        this.stringOptional3 = stringOptional3;
    }
}
