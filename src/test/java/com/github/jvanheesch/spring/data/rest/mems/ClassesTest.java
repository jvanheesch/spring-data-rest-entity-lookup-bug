package com.github.jvanheesch.spring.data.rest.mems;

import com.github.jvanheesch.spring.data.rest.Application;
import com.github.jvanheesch.spring.data.rest.BookService;
import com.github.jvanheesch.spring.data.rest.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.github.jvanheesch.spring.data.rest.mems.ClassesTest.Ctx;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// TODO: testen individueel lijken ok, dus t is een issue van unittestcleanup
@SpringBootTest
@ContextConfiguration(classes = Ctx.class)
class ClassesTest {

    @Autowired
    private BookService bookService;

    @Test
    void old_is_broken() {
        assertThatThrownBy(() -> {
            Book book = new Book();
            book.setTitle("exception");
            bookService.saveOld(book);
        }).isInstanceOf(Exception.class);

        List<Book> all = bookService.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    void new_is_fixed() {
        assertThatThrownBy(() -> {
            Book book = new Book();
            book.setTitle("exception");
            bookService.saveNew(book);
        }).isInstanceOf(Exception.class);

        List<Book> all = bookService.findAll();
        assertThat(all).hasSize(1);
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
