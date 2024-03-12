package com.example.employeeportal.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeNodeDto {

    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("user_email")
    public String userEmail;
    @JsonProperty("designation")
    public String designation;
}
