package com.company.bookstore.repository;

import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Book;
import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepoTest {

    @Autowired
    private BookRepo bookRepository;

    @Test
    public void shouldCreateBook() {
        Author author1 = new Author("John", "Smith", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "test@email.com");
        Publisher publisher1 = new Publisher();
        Book book = new Book("1234567890", LocalDate.now(), author1, "The Great Gatsby", publisher1, new BigDecimal("9.99"));
        bookRepository.save(book);
        Optional<Book> foundBook = bookRepository.findById(book.getId());
        assertThat(foundBook).isPresent();
    }

    @Test
    public void shouldUpdateBook() {
        Author author1 = new Author("John", "Smith", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "test@email.com");
        Publisher publisher1 = new Publisher();
        Book book = new Book("1234567890", LocalDate.now(), author1, "The Great Gatsby", publisher1, new BigDecimal("9.99"));
        bookRepository.save(book);

        book.setTitle("The Catcher in the Rye");
        bookRepository.save(book);

        Optional<Book> updatedBook = bookRepository.findById(book.getId());
        assertThat(updatedBook).isPresent();
        assertThat(updatedBook.get().getTitle()).isEqualTo("The Catcher in the Rye");
    }

    @Test
    public void shouldDeleteBook() {
        Author author1 = new Author("John", "Smith", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "test@email.com");
        Publisher publisher1 = new Publisher();
        Book book = new Book("1234567890", LocalDate.now(), author1, "The Great Gatsby", publisher1, new BigDecimal("9.99"));
        bookRepository.save(book);

        bookRepository.delete(book);

        Optional<Book> deletedBook = bookRepository.findById(book.getId());
        assertThat(deletedBook).isNotPresent();
    }


    @Test
    public void shouldSearchByAuthorID(){
        Author author1 = new Author("John", "Smith", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "test@email.com");
        Publisher publisher1 = new Publisher();
        Book book = new Book("1234567890", LocalDate.now(), author1, "The Great Gatsby", publisher1, new BigDecimal("9.99"));
        bookRepository.save(book);

        Optional<Book> foundBook = bookRepository.findById(book.getId());
        assertThat(foundBook).isPresent();
    }

    @Test
    public void shouldReadAll(){
        Author author1 = new Author("John", "Smith", "123 Main St", "Anytown", "NY", "12345", "555-555-1212", "test@email.com");
        Publisher publisher1 = new Publisher();
        Book book = new Book("1234567890", LocalDate.now(), author1, "The Great Gatsby", publisher1, new BigDecimal("9.99"));
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(1);
    }
}
