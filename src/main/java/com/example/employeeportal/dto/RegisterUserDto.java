package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDto {

    @NonNull
    @JsonProperty("first_name")
    private String firstName;

    @NonNull
    @JsonProperty("last_name")
    private String lastName;

    @NonNull
    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("emp_code")
    private String empCode;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("pod")
    private String pod;

    @JsonProperty("is_admin")
    private Boolean isAdmin = false;

    @NonNull
    @JsonProperty("password")
    private String password;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("request_user_email")
    private String requestUserEmail;

    @NonNull
    @JsonProperty("manager_email")
    private String managerEmail;

    @JsonProperty("dob")
    private String DOB;
}
