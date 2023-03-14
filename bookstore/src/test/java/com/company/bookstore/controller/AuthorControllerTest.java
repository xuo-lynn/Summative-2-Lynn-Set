package com.company.bookstore.controller;
import com.company.bookstore.Controller.AuthorController;
import com.company.bookstore.Model.Author;
import com.company.bookstore.Model.Publisher;
import com.company.bookstore.Repository.AuthorRepo;
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
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorRepo AuthorRepository;

    @Test
    public void testCreateAuthor() throws Exception {
        Author author = new Author();
        String inputJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(MockMvcRequestBuilders.post("/author").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Author author = new Author();
        AuthorRepository.save(author);
        String inputJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(MockMvcRequestBuilders.delete("/author/1")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        AuthorRepository.save(author);
        String inputJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(MockMvcRequestBuilders.put("/author/1").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAuthorById() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        AuthorRepository.save(author);
        String inputJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(MockMvcRequestBuilders.get("/author/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getAllCustomers() throws Exception {
        mockMvc.perform(get("/author")).andDo(print()).andExpect(status().isOk());
    }
}