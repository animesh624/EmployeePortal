package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetEmployeeDto {

    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("requested_user_email")
    private  String requestUserEmail;
}
