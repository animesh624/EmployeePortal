package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class GetEmailDto {
    public GetEmailDto() {}
    public GetEmailDto(String userEmail) {}

    @JsonProperty("user_email")
    private String userEmail;
}
