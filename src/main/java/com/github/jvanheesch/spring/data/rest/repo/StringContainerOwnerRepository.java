package com.github.jvanheesch.spring.data.rest.repo;

import com.github.jvanheesch.spring.data.rest.model.StringContainerOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface StringContainerOwnerRepository extends CrudRepository<StringContainerOwner, Long> {
    @RestResource
    @Override
    Iterable<StringContainerOwner> findAll();
}
