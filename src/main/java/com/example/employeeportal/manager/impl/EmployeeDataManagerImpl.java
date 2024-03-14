package com.example.employeeportal.manager.impl;


import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.repo.EmployeeDataRepo;
import com.example.employeeportal.repo.InterestsRepo;
import com.example.employeeportal.repo.LanguageRepo;
import com.example.employeeportal.repo.SkillsRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class EmployeeDataManagerImpl extends GenericManagerImpl<EmployeeData,String> implements EmployeeDataManager {

    EmployeeDataRepo employeeDataRepo;

    SkillsRepo skillsRepo;

    LanguageRepo languageRepo;

    InterestsRepo interestsRepo;

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
    public List<EmployeeData> searchEmployee(String name, String designation,String userEmail,String skill,String language,String interest) throws Exception{
        List<String> listUserEmail;
        if(name != null) {
            return employeeDataRepo.searchEmployeeByName(name);
        } else if(designation != null){
            return employeeDataRepo.searchEmployeeByDesignation(designation);
        } else if(userEmail != null){
            return employeeDataRepo.searchEmployeeByEmail(userEmail);
        }else if(skill != null){
            listUserEmail = skillsRepo.getUserEmailBySkill(skill);
        }else if(language != null){
            listUserEmail = languageRepo.getUserEmailByLanguage(language);
        }else{
            listUserEmail = interestsRepo.getUserEmailByInterest(interest);
        }
        List<EmployeeData> listEmployeeData = new ArrayList<>();
        for (String element : listUserEmail) {
            listEmployeeData.add(employeeDataRepo.findFirstByUserEmail(element));
        }
        return listEmployeeData;
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
