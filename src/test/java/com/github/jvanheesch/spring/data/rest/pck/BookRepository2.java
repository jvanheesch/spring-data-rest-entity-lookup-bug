package com.github.jvanheesch.spring.data.rest.pck;

import com.github.jvanheesch.spring.data.rest.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookRepository2 extends CrudRepository<Book, Long> {
}
