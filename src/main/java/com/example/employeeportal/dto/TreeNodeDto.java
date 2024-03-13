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
public class TreeNodeDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;


}
