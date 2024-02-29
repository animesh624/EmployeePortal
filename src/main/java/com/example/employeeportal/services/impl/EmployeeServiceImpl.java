package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.impl.GenericManagerImpl;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import com.example.employeeportal.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Override
    public EmployeeData getByUserName(String userName) throws Exception{
        EmployeeData employeeData = employeeDataManager.getByUserName(userName);

        if(employeeData == null){
            log.error("User doesnt exist with userName " + userName);
            return null;
        }
        return employeeData;
    }

    @Override
    public EmployeeData editEmployee(EmployeeDto employeeDto) throws Exception{
        EmployeeData employeeData = employeeDataManager.getByUserName(employeeDto.getUserName());
        if(employeeData == null){
            log.error("User doesnt exist with userName " + employeeDto.getUserName());
            return null;
        }

        employeeData.setLevel(employeeDto.getLevel());
        employeeData.setDesignation(employeeDto.getDesignation());
        employeeData.setContactNumber(employeeDto.getContactNumber());
        employeeDataManager.save(employeeData);

        return employeeData;
    }

    @Override
    public List<EmployeeData> searchEmployee(String keyword) throws Exception{
        return employeeDataManager.searchEmployee(keyword);
    }


}
