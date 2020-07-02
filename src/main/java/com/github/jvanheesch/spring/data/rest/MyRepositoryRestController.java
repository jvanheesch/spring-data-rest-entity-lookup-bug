package com.github.jvanheesch.spring.data.rest;

import com.github.jvanheesch.spring.data.rest.model.Book;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController
public class MyRepositoryRestController {
    private final BookService bookService;

    public MyRepositoryRestController(BookService bookService) {
        this.bookService = bookService;
    }

    // testen met postman toont dat dit werkt ...
    // mss is de beste integratie manier toch een sbtest?
    @PostMapping("/books")
    public ResponseEntity<PersistentEntityResource> createNonSwissOnlyDocument(@RequestBody  Book book, PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(assembler.toModel(this.bookService.saveOld(book)));
    }
}
