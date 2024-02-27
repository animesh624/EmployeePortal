package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterUserDto {

    private String name;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("emp_code")
    private String empCode;

    private String designation;

    private String level;

    @JsonProperty("contact_number")
    private String contactNumber;
}
