package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNeighboursDto {
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("requested_user_email")
    private  String requestUserEmail;
}
