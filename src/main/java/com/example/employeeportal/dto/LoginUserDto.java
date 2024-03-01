package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDto {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;
}
