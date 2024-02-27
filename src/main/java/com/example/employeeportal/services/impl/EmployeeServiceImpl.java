package com.example.employeeportal.services.impl;

import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import com.example.employeeportal.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDataRepo employeeDataRepo;

    @Override
    public EmployeeData getByUserName(String userName) throws Exception{
        EmployeeData employeeData = employeeDataRepo.findFirstByUserName(userName);

        if(employeeData == null){
            log.error("User doesnt exist with userName " + userName);
            return null;
        }
        return employeeData;
    }
}
