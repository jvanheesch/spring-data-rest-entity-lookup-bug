package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: testen individueel lijken ok, dus t is een issue van unittestcleanup
@DataJpaTest
// @ContextConfiguration(classes = Ctx.class)
class ClassesTest {

    // TODO: DataJpaTest wired blijkbaar de services niet ...
//    @Autowired
//    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

//    @Test
//    void old_is_broken() {
//        assertThatThrownBy(() -> {
//            Book book = new Book();
//            book.setTitle("exception");
//            bookService.saveOld(book);
//        }).isInstanceOf(Exception.class);
//
//        List<Book> all = bookService.findAll();
//        assertThat(all).hasSize(0);
//    }

//    @Test
//    void new_is_fixed() {
//        assertThatThrownBy(() -> {
//            Book book = new Book();
//            book.setTitle("exception");
//            bookService.saveNew(book);
//        }).isInstanceOf(Exception.class);
//
//        List<Book> all = bookService.findAll();
//        assertThat(all).hasSize(1);
//    }

//    @Test
//    void kak2() {
//        Book book = new Book();
//        book.setTitle("x");
//        bookService.saveNew(book);
//
//        List<Book> all = bookService.findAll();
//        assertThat(all).hasSize(1);
//    }
//
//    @Test
//    void kak3() {
//        Book book = new Book();
//        book.setTitle("x");
//        bookService.saveNew(book);
//
//        List<Book> all = bookService.findAll();
//        assertThat(all).hasSize(1);
//    }
//
    @Test
    void kak4() {
        System.out.println(bookRepository);
    }
}
