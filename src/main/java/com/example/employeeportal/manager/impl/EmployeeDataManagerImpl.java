package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeDataManagerImpl implements EmployeeDataManager {


    @Autowired
    EmployeeDataRepo employeeDataRepo;
    @Override
    public EmployeeData getByUserName (String userName) throws Exception{
        return employeeDataRepo.findFirstByUserName(userName);
    }
}
