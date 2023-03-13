package com.company.bookstore.controller;

import com.company.bookstore.Controller.BookController;
import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.BookRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookRepo bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;




    @Test
    public void shouldReadAllBooks() throws Exception {
        // when
        List<Book> books = bookRepository.findAll();
        Book book = new Book();
        book.setIsbn("1234567890123");
        book.setPublishDate(LocalDate.now());
        book.setTitle("Test Book");
        book.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book);

        Book book2 = new Book();
        book2.setIsbn("1234567890123");
        book2.setPublishDate(LocalDate.now());
        book2.setTitle("Test Book");
        book2.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setIsbn("1234567890123");
        book3.setPublishDate(LocalDate.now());
        book3.setTitle("Test Book");
        book3.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book3);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testFindBooksByAuthorId() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        author.setFirstName("Jonathan");
        author.setLastName("Scott");
        author.setStreet("North Palace Ave");
        author.setCity("Beverly Hills");
        author.setState("Washington");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("jscott449@gmail.com");

        Book book = new Book();
        book.setIsbn("1234567890123");
        book.setPublishDate(LocalDate.now());
        book.setTitle("Test Book");
        book.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book);

        Book book2 = new Book();
        book2.setIsbn("1234567890123");
        book2.setPublishDate(LocalDate.now());
        book2.setTitle("Test Book");
        book2.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book2);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/search/byauthor/1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCreateBook() throws Exception {
        Book book = new Book();
        book.setIsbn("1234567890123");
        book.setPublishDate(LocalDate.now());
        book.setTitle("Test Book");
        book.setPrice(new BigDecimal("24.99"));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))

                //expect 201 status code
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Book book = new Book();
        book.setIsbn("1234567890123");
        book.setPublishDate(LocalDate.now());
        book.setTitle("Test Book");
        book.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book);

        mockMvc.perform(delete("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))

                //expect 201 status code
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book book = new Book();
        book.setIsbn("1234567890123");
        book.setPublishDate(LocalDate.now());
        book.setTitle("Test Book");
        book.setPrice(new BigDecimal("24.99"));

        bookRepository.save(book);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))

                //expect 201 status code
                .andExpect(status().isOk());
    }
}
