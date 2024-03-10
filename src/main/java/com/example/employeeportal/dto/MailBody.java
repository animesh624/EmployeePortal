package com.example.employeeportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailBody {
    private String to;
    private String subject;
    private String text;
}