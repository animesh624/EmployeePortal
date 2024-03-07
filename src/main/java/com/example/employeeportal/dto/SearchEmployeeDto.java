package com.example.employeeportal.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchEmployeeDto {
    private String keyword;

    private String requestedUserEmail;
}
