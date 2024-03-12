package com.example.employeeportal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeDto {
    private String keyword;

    private String requestedUserEmail;
}
