package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditEmployeeDto {

    @NonNull
    @JsonProperty("first_name")
    private String firstName;

    @NonNull
    @JsonProperty("last_name")
    private String lastName;

    @NonNull
    @JsonProperty("user_email")
    private String userEmail;

    @NonNull
    @JsonProperty("manager_email")
    private String managerEmail;

    @NonNull
    @JsonProperty("emp_code")
    private String empCode;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("pod")
    private String pod;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("requested_user_email")
    private String requestedUserEmail;

    @JsonProperty("skills")
    private List<String> skills;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("interests")
    private List<String> interests;

    @JsonProperty("profile_urls")
    private List<NameUrlMapDto> profileUrls;

}
