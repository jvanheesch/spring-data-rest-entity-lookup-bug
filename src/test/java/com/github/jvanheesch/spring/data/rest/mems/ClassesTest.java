package com.github.jvanheesch.spring.data.rest.mems;

import com.github.jvanheesch.spring.data.rest.model.Book;
import com.github.jvanheesch.spring.data.rest.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void abc() {
        String json = "{\"title\":\"exception\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<String> request = new RequestEntity<>(json, headers, HttpMethod.POST, URI.create("http://localhost:" + port + "/books"));
        ResponseEntity<Void> bookResponseEntity = restTemplate.exchange(request, Void.class);

        List<Book> all = bookRepository.findAll();

        assertThat(all)
                .isEmpty();
    }

    @Test
    void def() {
        String json = "{\"title\":\"ok\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<String> request = new RequestEntity<>(json, headers, HttpMethod.POST, URI.create("http://localhost:" + port + "/books"));
        ResponseEntity<Void> bookResponseEntity = restTemplate.exchange(request, Void.class);

        List<Book> all = bookRepository.findAll();

        assertThat(all)
                .hasSize(1);
    }
}
