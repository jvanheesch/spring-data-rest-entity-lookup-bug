package com.github.jvanheesch.spring.data.rest.pck;

import com.github.jvanheesch.spring.data.rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository2 extends JpaRepository<Book, Long> {
}
