package com.example.employeeportal.dto;

import com.example.employeeportal.model.EmployeeData;
import lombok.Data;

import java.util.List;

@Data
public class SearchResultDto {

    List<EmployeeData> resultByName;

    List<EmployeeData> resultByEmail;

    List<EmployeeData> resultByInterest;

}
