package com.example.employeeportal.manager;

import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.model.EmployeeData;

import java.util.List;

public interface EmployeeDataManager extends GenericManager<EmployeeData,String>{

    EmployeeData getByUserName (String Username) throws Exception;

    SearchResultDto searchEmployee(String keyword) throws Exception;
}
