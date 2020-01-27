package com.github.jvanheesch.spring.data.rest.repo;

import com.github.jvanheesch.spring.data.rest.model.verdict.Verdict;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface VerdictRepository extends CrudRepository<Verdict, Long> {
    @Override
    Iterable<Verdict> findAll();
}
