package com.example.employeeportal.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Table(name = "employee_data_temp")
@Entity
public class EmployeeData {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "designation")
    private String designation;

    @Column(name = "level")
    private String level;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "dob")
    private String DOB;

    @Column(name = "date_created")
    private Date dateCreated = new Date();

    @Column(name ="date_modified")
    private Date dateModified = new Date();
}
