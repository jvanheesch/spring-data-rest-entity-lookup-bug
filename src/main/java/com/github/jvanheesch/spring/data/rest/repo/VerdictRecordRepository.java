package com.github.jvanheesch.spring.data.rest.repo;

import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface VerdictRecordRepository extends CrudRepository<VerdictRecord, Long> {
    @RestResource
    @Override
    Iterable<VerdictRecord> findAll();
}
