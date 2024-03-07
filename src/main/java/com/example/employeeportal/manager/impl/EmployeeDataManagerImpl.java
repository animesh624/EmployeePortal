package com.example.employeeportal.manager.impl;

import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.dto.TreeNodeDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public SearchResultDto searchEmployee(String keyword) throws Exception{
        SearchResultDto searchResultDto = SearchResultDto.builder().build();
//        searchResultDto.setResultByEmail(employeeDataRepo.findTop5ByUserEmailContainingOrderByFrequencyDesc(keyword));
//        searchResultDto.setResultByInterest(employeeDataRepo.findTop5ByInterestContainingOrderByFrequencyDesc(keyword));
//        searchResultDto.setResultByName(employeeDataRepo.findTop5ByFullNameContainingOrderByFrequencyDesc(keyword));
        return searchResultDto;
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
}
