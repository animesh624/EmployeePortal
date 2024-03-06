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

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("emp_code")
    private String empCode;

    private String designation;

    private String level;
    @JsonProperty("is_admin")
    private Boolean isAdmin;

    private String password;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("request_user_email")
    private String requestUserEmail;

    //image
}
