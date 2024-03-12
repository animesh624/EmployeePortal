package com.example.employeeportal.manager.impl;

import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EmployeeDataManagerImpl extends GenericManagerImpl<EmployeeData,String> implements EmployeeDataManager {

    EmployeeDataRepo employeeDataRepo;

    @Autowired
    public void setEmployeeDataRepo(EmployeeDataRepo employeeDataRepo){
        this.employeeDataRepo = employeeDataRepo;
        this.repository = employeeDataRepo;
    }



    @Override
    public EmployeeData getByUserEmail(String userEmail) throws Exception{
        return employeeDataRepo.findFirstByUserEmail(userEmail);
    }

    @Override
    public List<EmployeeData> searchEmployee(String name, String designation, String expertise ,String userEmail) throws Exception{

        if(name != null) {
            return employeeDataRepo.searchEmployeeByName(name);
        }
        else if(designation != null){
            return employeeDataRepo.searchEmployeeByDesignation(designation);
        }
        else if(userEmail != null){
            return employeeDataRepo.searchEmployeeByEmail(userEmail);
        }

        return new ArrayList<>();

    }

    @Override
    public EmployeeData getByEmpCode(String empCode) throws Exception{
        return employeeDataRepo.findFirstByEmpCode(empCode);
    };

    @Override
    public EmployeeData getEmpCodeDesignationNameByUserEmail (String userEmail) throws Exception{
        return employeeDataRepo.getEmpCodeDesignationNameByUserEmail(userEmail);
    }

    @Override
    public String getManagerEmailByUserEmail (String userEmail) throws Exception{
        return employeeDataRepo.getManagerEmailByUserEmail(userEmail);
    }

//    @Override
//    public EmployeeData searchEmployeeByEmail(String userEmail) throws Exception{
//        return employeeDataRepo.searchEmployeeByEmail(userEmail);
//    };
//
//    @Override
//    public EmployeeData searchEmployeeByDesignation(String designation) throws Exception{
//        return employeeDataRepo.searchEmployeeByDesignation(designation);
//    };
//
//    @Override
//    public EmployeeData searchEmployeeByName(String Name) throws Exception{
//        return employeeDataRepo.searchEmployeeByName(Name);
//    };
}
