package com.example.employeeportal.manager;

import com.example.employeeportal.model.EmployeeData;
public interface EmployeeDataManager extends GenericManager<EmployeeData,String>{

    EmployeeData getByUserName (String Username) throws Exception;
}
