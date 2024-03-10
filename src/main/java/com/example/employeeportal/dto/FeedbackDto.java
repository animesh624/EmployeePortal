package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto {
    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("message")
    private String message;
}