package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ManagerReporteeResponseDto {

    @JsonProperty("manager")
    public TreeNodeDto manager;

    @JsonProperty("reportee")
    public List<TreeNodeDto> reportee;

    @JsonProperty("user_email")
    public String userEmail;
}
