package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginUserDto {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;
}
