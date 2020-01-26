package com.github.jvanheesch.spring.data.rest.model.verdict;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "CONCLUSION_VERDICT")
public class Verdict {
    @Id
    @JsonProperty("value")
    private Long id;
    @JsonProperty("label")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO_JORIS: dit is goed voor fe !!!!!!
    public boolean isCompliant() {
        return "Compliant".equals(this.name);
    }
}
