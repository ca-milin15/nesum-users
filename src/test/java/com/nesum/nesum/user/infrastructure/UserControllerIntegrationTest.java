package com.nesum.nesum.user.infrastructure;

import java.time.LocalDateTime;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesum.nesum.user.application.dto.PhoneDTO;
import com.nesum.nesum.user.application.dto.UserDTO;

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
        var userAsString = objectMapper.writeValueAsString(user);
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(userAsString)
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
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

        // Si se ejecutan todos los test juntos, el correo ya existe en la base de datos. 
        // Si se ejecutan los test uno a uno, el correo no existe en la base de datos.

        //mockMvc.perform(
        //    MockMvcRequestBuilders.post("/user")
        //        .content(objectMapper.writeValueAsString(user))
        //        .contentType("application/json"))
        //.andExpect(MockMvcResultMatchers.status().isCreated());
        

        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje", Matchers.is("El correo ya registrado.")));
    }

    @Test
    @DisplayName("This method check different password format. Must be fail twice and success once")
    void userCreateEndPointFailPasswordFormatTest() throws Exception {
        var user = createPasswordWrongByLengthUserDTOObject();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").isNotEmpty());

        user = createPasswordWrongByFirstCharNumberUserDTOObject();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").isNotEmpty());

        user = createPasswordWrongByFirstCharNumberUserDTOObject();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").isNotEmpty());

        user = createPasswordOkDTOObject();
        mockMvc.perform(
            MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    private static UserDTO createPasswordWrongByLengthUserDTOObject() {
        var phoneList = List.of(new PhoneDTO("3122222", "34", "34"));
        return new UserDTO("UUID", LocalDateTime.now().toString(), LocalDateTime.now().toString(),
            "camilo Rivera", "camiloriveraa@dominio.cl", "passwordpasswordpasswordpassword", false, phoneList);
    }

    private static UserDTO createPasswordWrongByFirstCharNumberUserDTOObject() {
        var phoneList = List.of(new PhoneDTO("3122222", "34", "34"));
        return new UserDTO("UUID", LocalDateTime.now().toString(), LocalDateTime.now().toString(),
            "camilo Rivera", "camiloriveraa@dominio.cl", "1passwordpa", false, phoneList);
    }

    private static UserDTO createPasswordOkDTOObject() {
        var phoneList = List.of(new PhoneDTO("3122222", "34", "34"));
        return new UserDTO("UUID", LocalDateTime.now().toString(), LocalDateTime.now().toString(),
            "camilo Rivera", "camiloriveraa@dominio.cl", "passwordpa", false, phoneList);
    }
}
