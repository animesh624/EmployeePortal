package com.example.employeeportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;

@Data
@Table(name = "user_data_temp")
@Entity
public class UserData {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "date_created")
    private Date dateCreated = new Date();

    @Column(name ="date_modified")
    private Date dateModified = new Date();

}
