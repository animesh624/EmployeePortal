package com.example.employeeportal.dto;


import lombok.Data;

@Data
public class SearchEmployeeDto {
    private String keyword;

    private String requestedUserName;
}
