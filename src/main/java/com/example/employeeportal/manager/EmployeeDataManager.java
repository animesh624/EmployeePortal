package com.example.employeeportal.manager;

import com.example.employeeportal.model.EmployeeData;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeDataManager extends GenericManager<EmployeeData,String>{

    EmployeeData getByUserName (String Username) throws Exception;
}
