package com.github.jvanheesch.spring.data.rest.exception;

import com.github.jvanheesch.spring.data.rest.Application;
import com.github.jvanheesch.spring.data.rest.BookService;
import com.github.jvanheesch.spring.data.rest.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.github.jvanheesch.spring.data.rest.exception.ClassesTest.Ctx;

// lol idiot, bypassing service etc.
// TODO_JORIS: in huidige setup wordt db altijd proper opgekuist!
// TODO: testen individueel lijken ok, dus t is een issue van unittestcleanup
@DataJpaTest
@ContextConfiguration(classes = Ctx.class)
class ClassesTest {

    @Autowired
    private BookService bookService;

    @Test
    void test1() {
        try {
            Book book = new Book();
            book.setTitle("exception");
            bookService.saveOld(book);
        } catch (Exception e) {
            System.out.println("ClassesTest.test1");
        }
        List<Book> all = bookService.findAll();
        System.out.println(all);
    }

    @Test
    void test2() {
        List<Book> all;
        try {
            Book book = new Book();
            book.setTitle("exception");
            all = bookService.findAll();
            bookService.saveOld(book);
        } catch (Exception e) {
            System.out.println("ClassesTest.test2");
        }
        all = bookService.findAll();
        System.out.println(all);
    }

    @Test
    void test3() {
        try {
            Book book = new Book();
            book.setTitle("exception");
            bookService.saveOld(book);
        } catch (Exception e) {

            System.out.println("ClassesTest.test3");
        }
        List<Book> all = bookService.findAll();
        System.out.println(all);
    }

    @Configuration
    @ComponentScan(
            basePackageClasses = Application.class
//            includeFilters = @ComponentScan.Filter(
//                    type = FilterType.REGEX,
//                    pattern = "com\\.github\\.jvanheesch\\.spring\\.data\\.rest\\.pck\\.BookRepository2"
//            ),
//            useDefaultFilters = false
    )
    static class Ctx {
    }
}
