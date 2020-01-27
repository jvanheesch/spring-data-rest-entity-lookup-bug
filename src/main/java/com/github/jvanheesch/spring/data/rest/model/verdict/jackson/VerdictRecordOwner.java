package com.github.jvanheesch.spring.data.rest.model.verdict.jackson;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class VerdictRecordOwner {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<VerdictRecord> getVerdictRecord1() {
        Verdict verdict = new Verdict();
        verdict.setString("compliant");

        VerdictRecord verdictRecord1 = new VerdictRecord();
        verdictRecord1.setId(1L);
        verdictRecord1.setVerdict(verdict);

        return Optional.ofNullable(verdictRecord1);
    }

    public Optional<VerdictRecord> getVerdictRecord2() {
        VerdictRecord verdictRecord2 = new VerdictRecord();
        verdictRecord2.setId(2L);
        return Optional.ofNullable(verdictRecord2);
    }

    public Optional<VerdictRecord> getVerdictRecord3() {
        return Optional.empty();
    }

    // TODO_JORIS: evil ofc
    public Optional<VerdictRecord> getVerdictRecord4() {
        return null;
    }
}
