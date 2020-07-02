package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.pck.BookRepository2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;

import static com.github.jvanheesch.spring.data.rest.ClassesTest.Ctx;

@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = Ctx.class)
class ClassesTest {

    @Autowired
    private BookRepository2 bookRepository2;
    @Autowired
    private BookService bookService;
//    @Autowired
//    private ServiceB serviceB;

    @Test
    void whenBookAuthorGetterIsAnnotated_thenEntityLookupNoLongerWorks() {
        try {
            Book book = new Book();
            book.setTitle("exception");
            bookService.save(book);
        } catch (Exception e) {
            System.out.println(e);
        }
        boolean flaggedForRollback = TestTransaction.isFlaggedForRollback();

        List<Book> all = bookRepository2.findAll();
        System.out.println(all.size());
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
