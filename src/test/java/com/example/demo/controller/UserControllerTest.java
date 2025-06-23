package com.example.demo.controller;


import com.example.demo.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserModel sampleUser;

    @BeforeEach
    void setup() {
        sampleUser = new UserModel();
        sampleUser.setName("Test User");
        sampleUser.setUsername("testuser");
        sampleUser.setEmail("test@example.com");
        sampleUser.setPhone("123-456-7890");
        sampleUser.setWebsite("example.com");

    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("$[0].id", is(notNullValue())));
    }


    @Test
    void testGetUserById_notFound() throws Exception {
        mockMvc.perform(get("/users/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser_valid() throws Exception {
        String json = objectMapper.writeValueAsString(sampleUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", isA(Number.class)))
                .andExpect(jsonPath("$.name", is("Test User")));
    }


    @Test
    void testUpdateUser_notFound() throws Exception {
        String json = objectMapper.writeValueAsString(sampleUser);

        mockMvc.perform(put("/users/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }


    @Test
    void testDeleteUser_notFound() throws Exception {
        mockMvc.perform(delete("/users/9999"))
                .andExpect(status().isNotFound());
    }
}
