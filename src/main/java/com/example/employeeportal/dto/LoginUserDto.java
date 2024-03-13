package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Required;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    @NonNull
    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("password")
    private String password;
}
