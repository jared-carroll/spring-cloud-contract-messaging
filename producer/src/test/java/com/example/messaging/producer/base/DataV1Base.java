package com.example.messaging.producer.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMessageVerifier
public abstract class DataV1Base {
    @Autowired
    private WebApplicationContext webAppContext;

    public void doPostData() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();

        mockMvc.perform(post("/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"data\" : \"foo\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}
