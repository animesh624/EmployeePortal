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
public class GetEmployeeDto {

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("is_searched")
    private boolean isSearched;

    @JsonProperty("requested_user_email")
    private String requestUserEmail;
}
