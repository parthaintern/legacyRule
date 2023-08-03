package com.example.legacyRule.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LegacyReqParameters {

    @JsonProperty("a_number")
    private String aNumber;

    @JsonProperty("b_number")
    private String bNumber;

    @JsonProperty("content")
    private String content;

    @JsonProperty("dcs")
    private String dcs;

    @JsonProperty("imsi")
    private String imsi;

    @JsonProperty("pid")
    private String pid;

    @JsonProperty("raw_user_data")
    private String rawUserData;

    @JsonProperty("service_centre_address")
    private String serviceCentreAddress;

    @JsonProperty("service_centre_time_stamp")
    private String serviceCentreTimeStamp;

    @JsonProperty("tpdu_length")
    private String tpduLength;
}
