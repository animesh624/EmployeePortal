package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNeighboursDto {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("requested_user_name")
    private  String requestUserName;
}
