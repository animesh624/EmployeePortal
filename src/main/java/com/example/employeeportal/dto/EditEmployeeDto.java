package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EditEmployeeDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("emp_code")
    private String empCode;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("level")
    private String level;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("requested_user_email")
    private String requestedUserEmail;

    //image

}
