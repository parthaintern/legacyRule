package com.example.legacyRule.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LegacyReq {

    @JsonProperty("time_stamp")
    private Long timeStamp;

    @JsonProperty("server_key")
    private String serverKey;

    @JsonProperty("message_type")
    private Integer messageType;

    @JsonProperty("reference")
    private Long reference;

    @JsonProperty("calling_gt")
    private String callingGt;

    @JsonProperty("called_gt")
    private String calledGt;

    @JsonProperty("response_code")
    private Integer responseCode;

    @JsonProperty("cause")
    private String cause;

    @JsonProperty("parameters")
    private LegacyReqParameters legacyReqParameters;

}