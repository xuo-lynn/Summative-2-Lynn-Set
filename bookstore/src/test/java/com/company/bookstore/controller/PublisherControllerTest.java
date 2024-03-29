package com.company.bookstore.controller;

import com.company.bookstore.Controller.PublisherController;
import com.company.bookstore.Model.Publisher;

import com.company.bookstore.Repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PublisherRepository publisherRepository;

    @Test
    public void testCreatePublisher() throws Exception {
        Publisher publisher = new Publisher();

        String inputJson = objectMapper.writeValueAsString(publisher);
        mockMvc.perform(MockMvcRequestBuilders.post("/publishers").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetPublisherById() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisherRepository.save(publisher);
        String inputJson = objectMapper.writeValueAsString(publisher);
        mockMvc.perform(MockMvcRequestBuilders.get("/publishers/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetAllPublishers() throws Exception {
        Publisher publisher1 = new Publisher();
        Publisher publisher2 = new Publisher();
        Publisher publisher3 = new Publisher();
        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);
        publisherRepository.save(publisher3);
        mockMvc.perform(MockMvcRequestBuilders.get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePublisher() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisherRepository.save(publisher);

        publisher.setName("New Name");
        publisher.setStreet("456 Other St");
        publisher.setCity("Othertown");
        publisher.setState("NY");
        publisher.setPostalCode(54321);
        publisher.setPhone("111-111-1111");
        publisher.setEmail("test2@test.com");

        publisherRepository.save(publisher);
        String inputJson = objectMapper.writeValueAsString(publisher);

        mockMvc.perform(put("/publishers/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePublisher() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisherRepository.save(publisher);
        publisherRepository.deleteById(publisher.getId());
        mockMvc.perform(delete("/publishers/1"))
                .andExpect(status().isNoContent());

    }
}