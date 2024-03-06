package com.example.employeeportal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManagerReporteeResponseDto {
    public TreeNodeDto manager;
    public List<TreeNodeDto> reportee;
}
