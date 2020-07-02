package com.github.jvanheesch.spring.data.rest.repo;

import com.github.jvanheesch.spring.data.rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    default Book saveInNewTransaction(Book book) {
        return save(book);
    }
}
