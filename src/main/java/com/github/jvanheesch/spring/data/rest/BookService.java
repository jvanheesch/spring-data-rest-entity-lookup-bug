package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book save(Book book) {
        Book saved = saveInNewTransaction(book);
//        if (book.getTitle().equals("exception")) {
//            throw new RuntimeException();
//        }
        return saved;
    }

    @Transactional()
    Book saveInNewTransaction(Book book) {
        return bookRepository.save(book);
    }
}
