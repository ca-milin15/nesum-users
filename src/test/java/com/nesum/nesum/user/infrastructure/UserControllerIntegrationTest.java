package com.nesum.nesum.user.infrastructure;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("This method check the correct work of 'userCreateProcess' end-point")
    void userCreateEndPointOkTest() throws Exception {
        var user = UserControllerUnitTest.createOkUserDTOObject();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.isActive", Matchers.is(true)));
    }

    @Test
    @DisplayName("This method check the correct work of 'userCreateProcess' end-point"+
                 "when USerDTO fields are null")
    void userCreateEndPointFailMismatchFieldErrorTest() throws Exception {
        var user = UserControllerUnitTest.createWrongUserDTOObject();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").isNotEmpty());
    }

    @Test
    @DisplayName("This method check the correct work of 'userCreateProcess' end-point "+
                 "when e-mail value already exists in DB")
    void userCreateEndPointFailIntegrityErrorTest() throws Exception {
        var user = UserControllerUnitTest.createOkUserDTOObject();

        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isCreated());
        

        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje", Matchers.is("El correo ya registrado")));
    }
}
