package com.example.employeeportal.manager.impl;

import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDataManagerImpl extends GenericManagerImpl<EmployeeData,String> implements EmployeeDataManager {

    EmployeeDataRepo employeeDataRepo;

    @Autowired
    public void setEmployeeDataRepo(EmployeeDataRepo employeeDataRepo){
        this.employeeDataRepo = employeeDataRepo;
        this.repository = employeeDataRepo;
    }

    @Override
    public EmployeeData getByUserName(String userName) throws Exception{
        return employeeDataRepo.findFirstByUserName(userName);
    }

    @Override
    public SearchResultDto searchEmployee(String keyword) throws Exception{
        SearchResultDto searchResultDto = new SearchResultDto();
//        searchResultDto.setResultByEmail(employeeDataRepo.findTop5ByUserNameContainingOrderByFrequencyDesc(keyword));
//        searchResultDto.setResultByInterest(employeeDataRepo.findTop5ByInterestContainingOrderByFrequencyDesc(keyword));
//        searchResultDto.setResultByName(employeeDataRepo.findTop5ByFullNameContainingOrderByFrequencyDesc(keyword));
        return searchResultDto;
    }
}
