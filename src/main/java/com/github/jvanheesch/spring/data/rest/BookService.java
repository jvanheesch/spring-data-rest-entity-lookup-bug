package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    private BookService self;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book save(Book book) {
        Book saved = bookRepository.saveInNewTransaction(book);
        if (book.getTitle().equals("exception")) {
            throw new RuntimeException();
        }
        return saved;
    }

    // @Transactional
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }
}
