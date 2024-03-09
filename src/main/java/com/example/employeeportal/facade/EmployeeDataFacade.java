package com.example.employeeportal.facade;


import com.example.employeeportal.dto.EditEmployeeDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.InterestsManager;
import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.Interests;
import com.example.employeeportal.model.Languages;
import com.example.employeeportal.model.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataFacade {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    SkillsManager skillsManager;

    @Autowired
    LanguagesManager languagesManager;

    @Autowired
    InterestsManager interestsManager;

    public void saveEditEmployeeDetails(EmployeeData employeeData, EditEmployeeDto editEmployeeDto) throws Exception{
        employeeData.setLevel(editEmployeeDto.getLevel());
        employeeData.setDesignation(editEmployeeDto.getDesignation());
        employeeData.setContactNumber(editEmployeeDto.getContactNumber());
        employeeData.setManagerEmail(editEmployeeDto.getManagerEmail());
        employeeData.setFirstName(editEmployeeDto.getFirstName());
        employeeData.setLastName(editEmployeeDto.getLastName());
        employeeDataManager.save(employeeData);
    }

    public void saveSkillsLanguagesInterests(String userEmail, EditEmployeeDto editEmployeeDto) throws Exception{

        editEmployeeDto.getSkills().forEach(skill -> {
            saveEntryForSkill(userEmail,skill);
        });

        editEmployeeDto.getLanguages().forEach(language -> {
            saveEntryForLanguage(userEmail,language);
        });

        editEmployeeDto.getInterests().forEach(interest -> {
            saveEntryForInterest(userEmail,interest);
        });
    }

    private void saveEntryForSkill(String userEmail,String skill) {
        Skills skills = new Skills();
        skills.setUserEmail(userEmail);
        skills.setSkill(skill);
        skillsManager.save(skills);
    }

    private void saveEntryForLanguage(String userEmail,String language) {
        Languages languages = new Languages();
        languages.setLanguage(userEmail);
        languages.setLanguage(language);
    }

    private void saveEntryForInterest(String userEmail,String interest) {
        Interests interests = new Interests();
        interests.setUserEmail(userEmail);
        interests.setInterest(interest);
    }
}
