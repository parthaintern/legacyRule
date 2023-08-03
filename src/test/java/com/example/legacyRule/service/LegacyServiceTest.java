package com.example.legacyRule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import com.example.legacyRule.request.LegacyReq;
import com.example.legacyRule.request.LegacyReqParameters;
import com.example.legacyRule.entity.legacy_rules;
import com.example.legacyRule.repository.LegacyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LegacyServiceTest {

    @Mock
    private LegacyRepository legacyRepository;

    @InjectMocks
    private LegacyService legacyService;

    @BeforeEach
    public void setUp() {
        // Set up the behavior of the mock repository
        List<legacy_rules> mockData = new ArrayList<>();

        // Add some mock data to the list, similar to what you expect from the database
        legacy_rules legacy_rules = new legacy_rules();

        legacy_rules.setLrId(999);
        legacy_rules.setLrName("Rule999");
        legacy_rules.setLrCallingGt("%22%");
        legacy_rules.setLrMapGt("9479000023");
        legacy_rules.setLrAlphaBlocked(0);
        legacy_rules.setLrOa("%");
        legacy_rules.setLrDa("%");
        legacy_rules.setLrContent("test content");
        legacy_rules.setLrActionId(36);
        legacy_rules.setLrDeletedBy(1);
        legacy_rules.setLrCreatedAt("2023-01-31 12:13:38.235642");
        legacy_rules.setLrUpdatedAt("2023-04-03 15:21:18.405946");
        legacy_rules.setLrDeletedAt("2023-04-25 16:33:54.330108");
        legacy_rules.setLrCreatedBy(1);
        legacy_rules.setLrStatus(1);
        legacy_rules.setLrType("MO");
        legacy_rules.setLrUpdatedBy(1);
        legacy_rules.setLrRecalledAt(null);
        legacy_rules.setLrRecalledBy(null);
        legacy_rules.setLrMsisdn(null);
        legacy_rules.setLrSystemid(null);

        mockData.add(legacy_rules);

        when(legacyRepository.getAllValues()).thenReturn(mockData);
    }

    @Test
    public void testCheckLegacyRuleService() {

        // Create a test LegacyReq and LegacyReqParameters object
        LegacyReq legacyReq = new LegacyReq();

        legacyReq.setTimeStamp(1999999999999L);
        legacyReq.setServerKey("1001");
        legacyReq.setMessageType(3);
        legacyReq.setReference(3499352042L);
        legacyReq.setCallingGt("122222345");
        legacyReq.setCalledGt("911234500002");
        legacyReq.setResponseCode(0);
        legacyReq.setCause(null);

        LegacyReqParameters legacyReqParameters = new LegacyReqParameters();

        legacyReqParameters.setANumber("919535201758");
        legacyReqParameters.setBNumber("99645634756");
        legacyReqParameters.setContent("test content");
        legacyReqParameters.setDcs("0");
        legacyReqParameters.setImsi("1040023547528");
        legacyReqParameters.setPid("0");
        legacyReqParameters.setRawUserData("c8329bfd062dcb7374d8ce021ddf6f32a8f996bbd3eeb30b840cdbcba030c89d1e9741e4701e");
        legacyReqParameters.setServiceCentreAddress("911234500001");
        legacyReqParameters.setServiceCentreTimeStamp("23-05-02 20:17:23");
        legacyReqParameters.setTpduLength("43");

        legacyReq.setLegacyReqParameters(legacyReqParameters);

        // Call the method to be tested
        String result = legacyService.checkLegacyRuleService(legacyReq);

        // Assert the result based on your expectations
        assertEquals("""
                MATCH FOUND with CALLING_GT
                lr_id: -> 999
                LegacyRule calling_gt received:\t 122222345
                Database LegacyRule calling_gt:\t %22%
                Replaced Content Value:\t\t .*22.*""", result);
    }
}