package com.example.legacyRule.controller;

import com.example.legacyRule.request.LegacyReq;
import com.example.legacyRule.request.LegacyReqParameters;
import com.example.legacyRule.service.LegacyService;
import com.fasterxml.jackson.databind.ObjectMapper; // Import ObjectMapper
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest; // Import SpringBootTest
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Add SpringBootTest annotation
class LegacyControllerTest {
    private MockMvc mockMvc;

    @Autowired // Autowire the ObjectMapper bean
    private ObjectMapper objectMapper;

    @InjectMocks
    private LegacyService legacyService;

    @InjectMocks
    private LegacyController legacyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(legacyController).build();
    }

    @Test
    public void testCheckLegacyRule() throws Exception {
        // Prepare the input LegacyReq object for the test
        LegacyReq legacyReq = new LegacyReq();
        legacyReq.setCallingGt("9479001000");

        LegacyReqParameters legacyReqParameters = new LegacyReqParameters();
        legacyReqParameters.setContent("testing");

        legacyReq.setLegacyReqParameters(legacyReqParameters);

        // Mock the behavior of the LegacyService method
        when(legacyService.checkLegacyRuleService(any(LegacyReq.class)))
                .thenReturn("Mocked result from LegacyService");

        // Convert the LegacyReq object to a JSON string using the ObjectMapper
        String requestJson = objectMapper.writeValueAsString(legacyReq);

        // Perform the HTTP POST request and validate the response
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Mocked result from LegacyService"));

        // Verify the interaction with the LegacyService method
        verify(legacyService, times(1)).checkLegacyRuleService(any(LegacyReq.class));
    }
}



/*

package com.example.legacyRule.controller;

import com.example.legacyRule.request.LegacyReq;
import com.example.legacyRule.request.LegacyReqParameters;
import com.example.legacyRule.service.LegacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class LegacyControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private LegacyService legacyService;

    @InjectMocks
    private LegacyController legacyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(legacyController).build();
    }

    @Test
    public void testCheckLegacyRule() throws Exception {
        // Prepare the input LegacyReq object for the test
        LegacyReq legacyReq = new LegacyReq();
        legacyReq.setCallingGt("9479001000");

        LegacyReqParameters legacyReqParameters = new LegacyReqParameters();
        legacyReqParameters.setContent("testing");

        legacyReq.setLegacyReqParameters(legacyReqParameters);

//        String requestJson = objectMapper.writeValueAsString(legacyReq);

        // Mock the behavior of the LegacyService method
//        when(legacyService.checkLegacyRuleService(any(LegacyReq.class)))
//                .thenReturn("Mocked result from LegacyService");

        // Perform the HTTP POST request and validate the response
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(legacyReq))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Mocked result from LegacyService"));

        // Verify the interaction with the LegacyService method
        verify(legacyService, times(1)).checkLegacyRuleService(any(LegacyReq.class));
    }

}


 */