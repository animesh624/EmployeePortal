package com.example.employeeportal.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Data
@Table(name = "employee_data")
@Entity
public class EmployeeData {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "username")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "designation")
    private String designation;

    @Column(name = "level")
    private String level;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "date_created")
    private Date dateCreated = new Date();

    @Column(name ="date_modified")
    private Date dateModified = new Date();
}
