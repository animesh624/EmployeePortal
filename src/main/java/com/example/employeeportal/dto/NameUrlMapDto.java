package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NameUrlMapDto {

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("url")
    private String url;
}
