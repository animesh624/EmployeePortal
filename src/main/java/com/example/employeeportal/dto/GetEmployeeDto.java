package com.example.employeeportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetEmployeeDto {

    private String userName;

    private  String requestUserName;
}
