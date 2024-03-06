package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDto {
    private String name;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("emp_code")
    private String empCode;

    private String designation;

    private String level;

    @JsonProperty("contact_number")
    private String contactNumber;

    //image

}
