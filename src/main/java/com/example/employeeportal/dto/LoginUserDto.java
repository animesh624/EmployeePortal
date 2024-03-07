package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginUserDto {

    @NotNull("user_email")
    @JsonProperty("user_email")
    private String userEmail;

    @NotNull
    @JsonProperty("password")
    private String password;
}
