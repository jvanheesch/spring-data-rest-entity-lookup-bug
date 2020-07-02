package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book save(Book book) {
        Book saved = bookService.saveInNewTransaction(book);
        if (book.getTitle().equals("exception")) {
            throw new RuntimeException();
        }
        return saved;
    }

    @Transactional
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    Book saveInNewTransaction(Book book) {
        return bookRepository.save(book);
    }
}
