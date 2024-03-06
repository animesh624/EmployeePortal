package com.example.employeeportal.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TreeNodeDto {

    @JsonProperty("name")
    public String name;
    @JsonProperty("emp_code")
    public String empCode;
    @JsonProperty("designation")
    public String designation;
}
