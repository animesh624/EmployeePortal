package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetEmailDto {

        @JsonProperty("user_email")
        private String userEmail;

        @JsonProperty("requested_user_email")
        private String requestedUserEmail;
}
