package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeCodeDto {
    @JsonProperty("emp_code")
    private String empCode;

    @JsonCreator
    public EmployeeCodeDto(@JsonProperty("emp_code") String empCode) {
        this.empCode = empCode;
    }
}
