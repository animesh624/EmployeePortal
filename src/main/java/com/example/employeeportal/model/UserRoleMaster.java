package com.example.employeeportal.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_role_master_temp")
@Entity
public class UserRoleMaster {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "name")
    private String name;
}
