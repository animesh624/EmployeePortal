package com.example.employeeportal.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "manager_reportee_temp")
@Entity
public class ManagerReportee {

    @Id
    @Column(name = "reportee_email")
    private String reporteeEmail;

    @Column(name = "manager_email")
    private String managerEmail;

}
