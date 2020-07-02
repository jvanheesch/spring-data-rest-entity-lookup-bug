package com.github.jvanheesch.spring.data.rest.mems;

import com.github.jvanheesch.spring.data.rest.Application;
import com.github.jvanheesch.spring.data.rest.BookService;
import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.github.jvanheesch.spring.data.rest.mems.ClassesTest.Ctx;

@SpringBootTest(classes = Ctx.class)
class ClassesTest {

    @Autowired
    private BookRepository bookRepository;
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

        // TODO_JORIS: hier zou ik dus 0 results verwachten ... !!
        List<Book> all = bookService.findAll();
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
