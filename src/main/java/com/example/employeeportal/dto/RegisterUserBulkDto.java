package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RegisterUserBulkDto {

    @JsonProperty("bulk_list")
    private List<RegisterUserDto> bulkList;
}
