package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class GetEmailDto {

    @JsonProperty("user_email")
    private String userEmail;
}
