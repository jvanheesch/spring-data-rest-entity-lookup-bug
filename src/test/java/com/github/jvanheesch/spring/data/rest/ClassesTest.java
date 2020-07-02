package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.blah.Ctx;
import com.github.jvanheesch.spring.data.rest.pck.BookRepository2;
import com.github.jvanheesch.spring.data.rest.pck.ServiceB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = Ctx.class)
class ClassesTest {

    @Autowired
    private BookRepository2 bookRepository2;
//    @Autowired
//    private ServiceB serviceB;

    @Test
    void whenBookAuthorGetterIsAnnotated_thenEntityLookupNoLongerWorks() {
        System.out.println("");
    }
}
