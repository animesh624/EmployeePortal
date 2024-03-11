package com.example.employeeportal.facade;


import com.example.employeeportal.dto.EditEmployeeDto;
import com.example.employeeportal.manager.DocumentUrlManager;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.InterestsManager;
import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.manager.UserRoleMasterManager;
import com.example.employeeportal.model.DocumentUrl;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.Interests;
import com.example.employeeportal.model.Languages;
import com.example.employeeportal.model.ManagerReportee;
import com.example.employeeportal.model.Skills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Slf4j
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

    @Autowired
    UserRoleMasterManager userRoleMasterManager;

    @Autowired
    DocumentUrlManager documentUrlManager;

    @Autowired
    ManagerReporteeManager managerReporteeManager;

    public void saveEditEmployeeDetails(EmployeeData employeeData, EditEmployeeDto editEmployeeDto) throws Exception{

        ManagerReportee managerReportee = managerReporteeManager.getByReporteeEmailAndManagerEmail(employeeData.getManagerEmail(),employeeData.getUserEmail());
        if (managerReportee != null){
            managerReporteeManager.delete(managerReportee);
        }
        employeeData.setFirstName(editEmployeeDto.getFirstName());
        employeeData.setLastName(editEmployeeDto.getLastName());
        employeeData.setLevel(editEmployeeDto.getLevel());
        employeeData.setDesignation(editEmployeeDto.getDesignation());
        employeeData.setContactNumber(editEmployeeDto.getContactNumber());
        employeeData.setManagerEmail(editEmployeeDto.getManagerEmail());   // TODO : for mapping_table also we need to change the logic here
        employeeDataManager.save(employeeData);

        managerReportee = new ManagerReportee();
        managerReportee.setReporteeEmail(employeeData.getUserEmail());
        managerReportee.setManagerEmail(employeeData.getManagerEmail());
        managerReporteeManager.save(managerReportee);
    }

    public void saveSkillsLanguagesInterests(String userEmail, EditEmployeeDto editEmployeeDto) throws Exception{

        List<String> interestIds = userRoleMasterManager.getAllRoleIdByName(editEmployeeDto.getInterests());
        List<String> languageIds = userRoleMasterManager.getAllRoleIdByName(editEmployeeDto.getLanguages());
        List<String> skillIds = userRoleMasterManager.getAllRoleIdByName(editEmployeeDto.getSkills());

        log.info("Animesh 63 {}",interestIds);
        log.info("Animesh 64 {}",languageIds);
        log.info("Animesh 65 {}",skillIds);
        interestIds.forEach(roleId ->{
            try {
                saveEntryForSkill(userEmail,roleId);
            }catch (Exception e){

            }
        });

        languageIds.forEach(roleId -> {
            try {
                saveEntryForLanguage(userEmail, roleId);
            } catch (Exception e) {

            }
        });

        interestIds.forEach(interest -> {
            try {
                saveEntryForInterest(userEmail, interest);
            } catch (Exception e) {

            }
        });
    }

    public void saveProfileUrls(EditEmployeeDto editEmployeeDto) throws Exception{
//        List<String> profileLinkNames = editEmployeeDto.getProfileUrls().stream()
//                .map(NameUrlMapDto::getName)
//                .collect(Collectors.toList());
//        log.info("Animesh printing name {}",profileLinkNames);

//        editEmployeeDto.getProfileUrls().forEach( nameUrlMapDto -> {
//                     try{
//                         String roleId = userRoleMasterManager.getRoleIdByName(nameUrlMapDto.getName());
//                         DocumentUrl documentUrl = new DocumentUrl();
//                         documentUrl.setUrl(nameUrlMapDto.getUrl());
//                         documentUrl.setRoleId(roleId);
//                         documentUrlManager.save(documentUrl);
//                     } catch (Exception e){
//
//                     }
//             }
//        );

        editEmployeeDto.getProfileUrls().forEach(nameUrlMapDto -> {
            try {
                DocumentUrl documentUrl = documentUrlManager.getByUserEmailAndName(editEmployeeDto.getUserEmail(), nameUrlMapDto.getName());
                if(documentUrl == null){
                    documentUrl = new DocumentUrl();
                }
                documentUrl.setDocumentName(nameUrlMapDto.getName());
                documentUrl.setUrl(nameUrlMapDto.getUrl());
                documentUrl.setUserEmail(editEmployeeDto.getUserEmail());
                documentUrlManager.save(documentUrl);
            }catch (Exception e){

            }
        });
    }

    private void saveEntryForSkill(String userEmail,String skill) throws Exception{
        Skills skills = skillsManager.getByUserEmailAndRoleId(userEmail,skill);
        if(skills == null) {
             skills = new Skills();
             skills.setUserEmail(userEmail);
             skills.setSkill(skill);
             skillsManager.save(skills);
        }
    }

    private void saveEntryForLanguage(String userEmail,String language) throws Exception{
        Languages languages = languagesManager.getByUserEmailAndRoleId(userEmail,language);
        if(languages == null){
            languages = new Languages();
            languages.setUserEmail(userEmail);
            languages.setLanguage(language);
            languagesManager.save(languages);
        }

    }

    private void saveEntryForInterest(String userEmail,String interest) throws Exception{
        Interests interests = interestsManager.getByUserEmailAndRoleId(userEmail,interest);
        if(interest == null){
            interests = new Interests();
            interests.setUserEmail(userEmail);
            interests.setInterest(interest);
            interestsManager.save(interests);
        }

    }
}
