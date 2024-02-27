package com.example.employeeportal.manager;

import com.example.employeeportal.model.EmployeeData;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeDataManager {

    EmployeeData getByUserName (String Username) throws Exception;
}
