package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterUserDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("emp_code")
    private String empCode;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("level")
    private String level;

    @JsonProperty("is_admin")
    private Boolean isAdmin;

    @JsonProperty("password")
    private String password;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("request_user_email")
    private String requestUserEmail;

    @JsonProperty("manager_email")
    private String managerEmail;
}
