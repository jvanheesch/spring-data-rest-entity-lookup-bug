package com.github.jvanheesch.spring.data.rest.mems;

import com.github.jvanheesch.spring.data.rest.Application;
import com.github.jvanheesch.spring.data.rest.BookService;
import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.github.jvanheesch.spring.data.rest.mems.ClassesTest.Ctx;

// TODO_JORIS: in huidige setup wordt db altijd proper opgekuist!
// TODO: testen individueel lijken ok, dus t is een issue van unittestcleanup
@DataJpaTest
@ContextConfiguration(classes = Ctx.class)
class ClassesTest {

    @Autowired
    private BookRepository bookService;

    @Test
    void test1() {
        Book book = new Book();
        book.setTitle("mems");
        bookService.save(book);
        List<Book> all = bookService.findAll();
        System.out.println(all);
    }

    @Test
    void test2() {
        Book book = new Book();
        book.setTitle("mems");
        bookService.save(book);
        List<Book> all = bookService.findAll();
        System.out.println(all);
    }

    @Test
    void test3() {
        Book book = new Book();
        book.setTitle("mems");
        bookService.save(book);
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
