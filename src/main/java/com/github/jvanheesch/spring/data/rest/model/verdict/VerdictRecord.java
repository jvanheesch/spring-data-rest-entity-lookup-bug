package com.github.jvanheesch.spring.data.rest.model.verdict;

import javax.persistence.*;

// TODO_JORIS rename?

@Entity
@Table(name = "VERDICT_RECORD")
@SequenceGenerator(allocationSize = 1, name = "VERDICT_RECORD_IDGEN", sequenceName = "VERDICT_RECORD_SEQ")
public class VerdictRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERDICT_RECORD_IDGEN")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Verdict verdict;

    public Long getId() {
        return id;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
