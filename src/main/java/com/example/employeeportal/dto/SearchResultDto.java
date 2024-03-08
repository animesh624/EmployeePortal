package com.example.employeeportal.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class SearchResultDto {

    List<Object> searchResult;
}
