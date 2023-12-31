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
                MATCH FOUND with CALLING_GT
                lr_id: -> 1001
                LegacyRule calling_gt received:\t 874723434
                Database LegacyRule calling_gt:\t %234%
                Replaced Content Value:\t\t .*234.*""";

        String expectedResult3 = """
                MATCH FOUND with CONTENT
                lr_id: -> 1000
                LegacyRule content received:\t test content
                Database LegacyRule content:\t test content
                Replaced Content Value:\t\t test content""";

        String expectedResult4 = """
                MATCH FOUND with CALLING_GT
                lr_id: -> 1002
                LegacyRule calling_gt received:\t 9876a90
                Database LegacyRule calling_gt:\t 9876[a-b]__
                Replaced Content Value:\t\t 9876[a-b][0-9A-Za-z][0-9A-Za-z]""";

        String expectedResult5 = """
                MATCH FOUND with CONTENT
                lr_id: -> 1003
                LegacyRule content received:\t super spam yes
                Database LegacyRule content:\t %spam%
                Replaced Content Value:\t\t .*spam.*""";

        return Stream.of(

                Arguments.of(createLegacyReq(1999999999999L, "1001", 3, 34999999L, "122222345", "911234500002", 0, null, "919535201758", "996473847383", "oooo", "0", "10400234556784", "0", "ca34283bbcd3432489cadf2834", "911234500002", "23-05-02 20:17:23", "43"), expectedResult1),

                Arguments.of(createLegacyReq(1999999999999L, "1001", 3, 34999999L, "874723434", "911234500002", 0, null, "919535201758", "996473847383", "mmmm", "0", "10400234556784", "0", "ca34283bbcd3432489cadf2834", "911234500002", "23-05-02 20:17:23", "43"), expectedResult2),

                Arguments.of(createLegacyReq(1999999999999L, "1001", 3, 34999999L, "1", "911234500002", 0, null, "919535201758", "996473847383", "test content", "0", "10400234556784", "0", "ca34283bbcd3432489cadf2834", "911234500002", "23-05-02 20:17:23", "43"), expectedResult3),

                Arguments.of(createLegacyReq(1999999999999L, "1001", 3, 34999999L, "9876a90", "911234500002", 0, null, "919535201758", "996473847383", "oooo", "0", "10400234556784", "0", "ca34283bbcd3432489cadf2834", "911234500002", "23-05-02 20:17:23", "43"), expectedResult4),

                Arguments.of(createLegacyReq(1999999999999L, "1001", 3, 34999999L, "99999999", "911234500002", 0, null, "919535201758", "996473847383", "super spam yes", "0", "10400234556784", "0", "ca34283bbcd3432489cadf2834", "911234500002", "23-05-02 20:17:23", "43"), expectedResult5)
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

        legacy_rules legacy_rules3 = new legacy_rules();
        legacy_rules3.setLrId(1002);
        legacy_rules3.setLrCallingGt("9876[a]__");
        legacy_rules3.setLrContent("test content");

        legacy_rules legacy_rules4 = new legacy_rules();
        legacy_rules3.setLrId(1003);
        legacy_rules3.setLrCallingGt("%");
        legacy_rules3.setLrContent("%spam%");

        mockData.add(legacy_rules1);
        mockData.add(legacy_rules2);
        mockData.add(legacy_rules3);

        return mockData;
    }

    private static LegacyReq createLegacyReq(Long TimeStamp, String ServerKey, Integer MessageType, Long Reference, String CallingGt, String CalledGt, Integer ResponseCode, String Cause, String aNumber, String bNumber, String content, String dcs, String imsi, String pid, String rawUserData, String serviceCentreAddress, String serviceCentreTimeStamp, String tpduLength) {

        LegacyReq legacyReq = new LegacyReq();
        legacyReq.setTimeStamp(TimeStamp);
        legacyReq.setServerKey(ServerKey);
        legacyReq.setMessageType(MessageType);
        legacyReq.setReference(Reference);
        legacyReq.setCallingGt(CallingGt);
        legacyReq.setCalledGt(CalledGt);
        legacyReq.setResponseCode(ResponseCode);
        legacyReq.setCause(Cause);

        LegacyReqParameters legacyReqParameters = new LegacyReqParameters();
        legacyReqParameters.setANumber(aNumber);
        legacyReqParameters.setBNumber(bNumber);
        legacyReqParameters.setContent(content);
        legacyReqParameters.setDcs(dcs);
        legacyReqParameters.setImsi(imsi);
        legacyReqParameters.setPid(pid);
        legacyReqParameters.setRawUserData(rawUserData);
        legacyReqParameters.setServiceCentreAddress(serviceCentreAddress);
        legacyReqParameters.setServiceCentreTimeStamp(serviceCentreTimeStamp);
        legacyReqParameters.setTpduLength(tpduLength);

        legacyReq.setLegacyReqParameters(legacyReqParameters);

        return legacyReq;
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