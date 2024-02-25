package com.loomboom.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReCaptchaResponse {
    
    @JsonProperty("success")
    private boolean isSuccess;

    @JsonProperty("challenge_ts")
    private String challengeTimestamp;

    @JsonProperty("hostname")
    private String hostname;

    @JsonProperty("error-codes")
    private String[] errorCodes;

}
