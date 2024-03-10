package com.example.employeeportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NameUrlMapDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
