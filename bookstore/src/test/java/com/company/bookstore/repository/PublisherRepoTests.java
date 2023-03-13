package com.company.bookstore.repository;

import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PublisherRepoTests {

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @Transactional
    public void testCreatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("John");
        publisher.setEmail("john.doe@example.com");
        publisherRepository.save(publisher);

        Publisher savedPublisher = publisherRepository.findById(publisher.getId()).orElse(null);
        assertNotNull(savedPublisher);
        assertEquals(publisher.getName(), savedPublisher.getName());
        assertEquals(publisher.getEmail(), savedPublisher.getEmail());
    }

    @Test
    @Transactional
    public void testUpdatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("John");
        publisher.setEmail("john.doe@example.com");
        publisherRepository.save(publisher);

        publisher.setName("Jane");
        publisher.setEmail("jane.doe@example.com");
        publisherRepository.save(publisher);

        Publisher updatedPublisher = publisherRepository.findById(publisher.getId()).orElse(null);
        assertNotNull(updatedPublisher);
        assertEquals(publisher.getName(), updatedPublisher.getName());
        assertEquals(publisher.getEmail(), updatedPublisher.getEmail());
    }

    @Test
    @Transactional
    public void testDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("John");
    }

    @Test
    @Transactional
    public void testReadAllPublishers() {
        Publisher publisher = new Publisher();
        publisher.setName("John");
        publisher.setEmail("test@gmail.com");
        publisherRepository.save(publisher);
    }

    @Test
    @Transactional
    public void testReadPublisherById() {
        Publisher publisher = new Publisher();
        publisher.setName("John");
        publisher.setEmail("test@gmail.com");
        publisherRepository.save(publisher);
    }
}

