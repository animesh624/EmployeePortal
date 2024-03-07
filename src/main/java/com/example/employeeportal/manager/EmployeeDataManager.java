package com.example.employeeportal.manager;

import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.dto.TreeNodeDto;
import com.example.employeeportal.model.EmployeeData;

import java.util.List;

public interface EmployeeDataManager extends GenericManager<EmployeeData,String>{

    EmployeeData getByUserEmail (String userEmail) throws Exception;

    SearchResultDto searchEmployee(String keyword) throws Exception;

    EmployeeData getByEmpCode(String empCode) throws Exception;

    EmployeeData getEmpCodeDesignationNameByUserEmail (String userEmail) throws Exception;

    String getManagerEmailByUserEmail (String userEmail) throws Exception;
}
