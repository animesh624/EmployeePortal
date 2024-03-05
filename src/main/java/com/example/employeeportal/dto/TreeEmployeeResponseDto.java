package com.example.employeeportal.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeEmployeeResponseDto {
    private TreeEmployeeDto manager;
    private List<TreeEmployeeDto> children;

}
