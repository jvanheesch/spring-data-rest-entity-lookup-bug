package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.blah.Ctx;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Ctx.class)
//@ContextConfiguration(classes = Ctx.class)
//@EnableAutoConfiguration
class ClassesTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void whenBookAuthorGetterIsAnnotated_thenEntityLookupNoLongerWorks() {
        System.out.println("");
    }
}
