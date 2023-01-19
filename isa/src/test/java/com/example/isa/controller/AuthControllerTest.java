package com.example.isa.controller;

import com.example.isa.AbstractTest;
import com.example.isa.controller.dto.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AuthControllerTest extends AbstractTest {

    @Test
    public void login() throws Exception {
        final LoginDto login = new LoginDto();
        login.setEmail("nebojsa@gmail.com");
        login.setPassword("123");
        final MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content((login).toString());
        mockMvc.perform(loginRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
