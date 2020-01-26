package com.github.jvanheesch.spring.data.rest.repo;

import com.github.jvanheesch.spring.data.rest.model.verdict.jackson.VerdictRecordOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface VerdictRecordOwnerRepository extends CrudRepository<VerdictRecordOwner, Long> {
    @RestResource
    @Override
    Iterable<VerdictRecordOwner> findAll();
}
