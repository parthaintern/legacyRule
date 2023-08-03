package com.example.legacyRule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.legacyRule.request.LegacyReq;
import com.example.legacyRule.request.LegacyReqParameters;
import com.example.legacyRule.entity.legacy_rules;
import com.example.legacyRule.repository.LegacyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class LegacyServiceTest {

    @Mock
    private LegacyRepository legacyRepository;

    @InjectMocks
    private LegacyService legacyService;

    @BeforeEach
    public void setUp() {

        List<legacy_rules> mockData = mockLegacyRules();

        when(legacyRepository.getAllValues()).thenReturn(mockData);
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testCheckLegacyRuleService(LegacyReq legacyReq, String expectedOutput) {

        String result = legacyService.checkLegacyRuleService(legacyReq);
        assertEquals(expectedOutput, result);

    }

    static Stream<Arguments> provideTestCases() {
        String expectedResult1 = """
                MATCH FOUND with CALLING_GT
                lr_id: -> 1000
                LegacyRule calling_gt received:\t 122222345
                Database LegacyRule calling_gt:\t %22%
                Replaced Content Value:\t\t .*22.*""";

        String expectedResult2 = """
                MATCH NOT FOUND!
                calling_gt received: 87485
                content received: mmmm""";

        return Stream.of(

                Arguments.of(createLegacyReq1(), expectedResult1),

                Arguments.of(createLegacyReq2(), expectedResult2)
        );
    }


    private static List<legacy_rules> mockLegacyRules() {

        List<legacy_rules> mockData = new ArrayList<>();

        legacy_rules legacy_rules1 = new legacy_rules();

        legacy_rules1.setLrId(1000);
        legacy_rules1.setLrName("Rule999");
        legacy_rules1.setLrCallingGt("%22%");
        legacy_rules1.setLrMapGt("9479000023");
        legacy_rules1.setLrAlphaBlocked(0);
        legacy_rules1.setLrOa("%");
        legacy_rules1.setLrDa("%");
        legacy_rules1.setLrContent("test content");
        legacy_rules1.setLrActionId(36);
        legacy_rules1.setLrDeletedBy(1);
        legacy_rules1.setLrCreatedAt("2023-01-31 12:13:38.235642");
        legacy_rules1.setLrUpdatedAt("2023-04-03 15:21:18.405946");
        legacy_rules1.setLrDeletedAt("2023-04-25 16:33:54.330108");
        legacy_rules1.setLrCreatedBy(1);
        legacy_rules1.setLrStatus(1);
        legacy_rules1.setLrType("MO");
        legacy_rules1.setLrUpdatedBy(1);
        legacy_rules1.setLrRecalledAt(null);
        legacy_rules1.setLrRecalledBy(null);
        legacy_rules1.setLrMsisdn(null);
        legacy_rules1.setLrSystemid(null);

        legacy_rules legacy_rules2 = new legacy_rules();

        legacy_rules2.setLrId(1001);
        legacy_rules2.setLrName("Rule1000");
        legacy_rules2.setLrCallingGt("%234%");
        legacy_rules2.setLrMapGt("9479000023");
        legacy_rules2.setLrAlphaBlocked(0);
        legacy_rules2.setLrOa("%");
        legacy_rules2.setLrDa("%");
        legacy_rules2.setLrContent("another test");
        legacy_rules2.setLrActionId(36);
        legacy_rules2.setLrDeletedBy(1);
        legacy_rules2.setLrCreatedAt("2023-01-31 12:13:38.235642");
        legacy_rules2.setLrUpdatedAt("2023-04-03 15:21:18.405946");
        legacy_rules2.setLrDeletedAt("2023-04-25 16:33:54.330108");
        legacy_rules2.setLrCreatedBy(1);
        legacy_rules2.setLrStatus(1);
        legacy_rules2.setLrType("MO");
        legacy_rules2.setLrUpdatedBy(1);
        legacy_rules2.setLrRecalledAt(null);
        legacy_rules2.setLrRecalledBy(null);
        legacy_rules2.setLrMsisdn(null);
        legacy_rules2.setLrSystemid(null);

        mockData.add(legacy_rules1);
        mockData.add(legacy_rules2);

        return mockData;
    }


    private static LegacyReq createLegacyReq1() {
        LegacyReq legacyReq1 = new LegacyReq();

        legacyReq1.setTimeStamp(1999999999999L);
        legacyReq1.setServerKey("1001");
        legacyReq1.setMessageType(3);
        legacyReq1.setReference(3499352042L);
        legacyReq1.setCallingGt("122222345");
        legacyReq1.setCalledGt("911234500002");
        legacyReq1.setResponseCode(0);
        legacyReq1.setCause(null);

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

        legacyReq1.setLegacyReqParameters(legacyReqParameters);

        return legacyReq1;
    }

    private static LegacyReq createLegacyReq2() {
        LegacyReq legacyReq2 = new LegacyReq();

        legacyReq2.setTimeStamp(1999999999999L);
        legacyReq2.setServerKey("1001");
        legacyReq2.setMessageType(3);
        legacyReq2.setReference(3499352042L);
        legacyReq2.setCallingGt("87485");
        legacyReq2.setCalledGt("911234500002");
        legacyReq2.setResponseCode(0);
        legacyReq2.setCause(null);

        LegacyReqParameters legacyReqParameters = new LegacyReqParameters();

        legacyReqParameters.setANumber("919535201758");
        legacyReqParameters.setBNumber("99645634756");
        legacyReqParameters.setContent("mmmm");
        legacyReqParameters.setDcs("0");
        legacyReqParameters.setImsi("1040023547528");
        legacyReqParameters.setPid("0");
        legacyReqParameters.setRawUserData("c8329bfd062dcb7374d8ce021ddf6f32a8f996bbd3eeb30b840cdbcba030c89d1e9741e4701e");
        legacyReqParameters.setServiceCentreAddress("911234500001");
        legacyReqParameters.setServiceCentreTimeStamp("23-05-02 20:17:23");
        legacyReqParameters.setTpduLength("43");

        legacyReq2.setLegacyReqParameters(legacyReqParameters);
        return legacyReq2;
    }

}

//    legacy_rules legacy_rules1 = new legacy_rules();
//
//        legacy_rules.setLrId(999);
//                legacy_rules.setLrName("Rule999");
//                legacy_rules.setLrCallingGt("%22%");
//                legacy_rules.setLrMapGt("9479000023");
//                legacy_rules.setLrAlphaBlocked(0);
//                legacy_rules.setLrOa("%");
//                legacy_rules.setLrDa("%");
//                legacy_rules.setLrContent("test content");
//                legacy_rules.setLrActionId(36);
//                legacy_rules.setLrDeletedBy(1);
//                legacy_rules.setLrCreatedAt("2023-01-31 12:13:38.235642");
//                legacy_rules.setLrUpdatedAt("2023-04-03 15:21:18.405946");
//                legacy_rules.setLrDeletedAt("2023-04-25 16:33:54.330108");
//                legacy_rules.setLrCreatedBy(1);
//                legacy_rules.setLrStatus(1);
//                legacy_rules.setLrType("MO");
//                legacy_rules.setLrUpdatedBy(1);
//                legacy_rules.setLrRecalledAt(null);
//                legacy_rules.setLrRecalledBy(null);
//                legacy_rules.setLrMsisdn(null);
//                legacy_rules.setLrSystemid(null);