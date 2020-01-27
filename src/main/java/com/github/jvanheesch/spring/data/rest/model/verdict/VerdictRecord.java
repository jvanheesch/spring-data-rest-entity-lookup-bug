package com.github.jvanheesch.spring.data.rest.model.verdict;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VerdictRecord {
    @Id
    private Long id;
    // TODO_JORIS: lazy
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict value;

    public VerdictRecord(Verdict value) {
        this.value = value;
    }

    public VerdictRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Verdict getValue() {
        return value;
    }

    public void setValue(Verdict value) {
        this.value = value;
    }
}
