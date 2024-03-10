package com.example.employeeportal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user_forgot_password")
@Entity
@Builder
@AllArgsConstructor
public class ForgotPasswordData {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "otp")
    private String otp;

    @Column(name = "expiration_time")
    private Date expirationTime;

    public ForgotPasswordData() {}
}