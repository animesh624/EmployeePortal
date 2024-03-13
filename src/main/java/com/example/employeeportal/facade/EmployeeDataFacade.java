package com.example.employeeportal.facade;


import com.example.employeeportal.dto.EditEmployeeDto;
import com.example.employeeportal.manager.DocumentUrlManager;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.InterestsManager;
import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.manager.UserRoleMasterManager;
import com.example.employeeportal.model.DocumentUrl;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.Interests;
import com.example.employeeportal.model.Languages;
import com.example.employeeportal.model.ManagerReportee;
import com.example.employeeportal.model.Skills;
import com.example.employeeportal.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;



@Slf4j
@Component
public class EmployeeDataFacade {

    private EmployeeDataManager employeeDataManager;
    private SkillsManager skillsManager;
    private LanguagesManager languagesManager;
    private InterestsManager interestsManager;
    private UserRoleMasterManager userRoleMasterManager;
    private DocumentUrlManager documentUrlManager;
    private ManagerReporteeManager managerReporteeManager;
    private UserDataManager userDataManager;

    @Autowired
    public EmployeeDataFacade(EmployeeDataManager employeeDataManager,
                                 SkillsManager skillsManager,
                                 LanguagesManager languagesManager,
                                 InterestsManager interestsManager,
                                 UserRoleMasterManager userRoleMasterManager,
                                 DocumentUrlManager documentUrlManager,
                                 ManagerReporteeManager managerReporteeManager,
                                 UserDataManager userDataManager) {
        this.employeeDataManager = employeeDataManager;
        this.skillsManager = skillsManager;
        this.languagesManager = languagesManager;
        this.interestsManager = interestsManager;
        this.userRoleMasterManager = userRoleMasterManager;
        this.documentUrlManager = documentUrlManager;
        this.managerReporteeManager = managerReporteeManager;
        this.userDataManager = userDataManager;
    }

    public void saveEditEmployeeDetails(EmployeeData employeeData, EditEmployeeDto editEmployeeDto) throws Exception{

        if(StringUtils.isEmpty(employeeDataManager.getManagerEmailByUserEmail(editEmployeeDto.getManagerEmail()))){
            return;
        }

        ManagerReportee managerReportee = managerReporteeManager.getByReporteeAndManager(employeeData.getUserEmail(),employeeData.getManagerEmail());
        if (managerReportee != null){
            managerReporteeManager.delete(managerReportee);
        }

        employeeData.setFirstName(editEmployeeDto.getFirstName());
        employeeData.setLastName(editEmployeeDto.getLastName());
        employeeData.setPod(editEmployeeDto.getPod());
        employeeData.setDesignation(editEmployeeDto.getDesignation());
        employeeData.setContactNumber(editEmployeeDto.getContactNumber());
        employeeData.setManagerEmail(editEmployeeDto.getManagerEmail());  // TODO : for mapping_table also we need to change the logic here
        employeeDataManager.save(employeeData);

        managerReportee = buildManagerReportee(employeeData);
        managerReporteeManager.save(managerReportee);
    }

    public void saveEditUserDetails(EditEmployeeDto editEmployeeDto) throws Exception{
        log.info("Animesh at 83 with editEmployeeDto.getUserEmail() {}",editEmployeeDto.getUserEmail());
        UserData userData = userDataManager.getByUserEmail(editEmployeeDto.getUserEmail());
        log.info("Animesh at line 84 with userData {}",userData);
        if(userData == null){
            throw new Exception("User data doesnt exists for userEmail "+ editEmployeeDto.getUserEmail());
        }

        userData.setFirstName(editEmployeeDto.getFirstName());
        userData.setLastName(editEmployeeDto.getLastName());
        userDataManager.save(userData);
    }

    private ManagerReportee buildManagerReportee(EmployeeData employeeData) throws Exception{
        return ManagerReportee.builder()
                .reporteeEmail(employeeData.getUserEmail())
                .managerEmail(employeeData.getManagerEmail())
                .build();
    }


    // TODO : Please see if any map or something can be applied for better code quality
    public void saveSkillsLanguagesInterests(String userEmail, EditEmployeeDto editEmployeeDto) throws Exception{

        List<String> interestIds = userRoleMasterManager.getAllRoleIdByName(editEmployeeDto.getInterests());
        List<String> languageIds = userRoleMasterManager.getAllRoleIdByName(editEmployeeDto.getLanguages());
        List<String> skillIds = userRoleMasterManager.getAllRoleIdByName(editEmployeeDto.getSkills());

        interestIds.forEach(roleId ->{
            try {
                saveEntryForInterest(userEmail,roleId);
            }catch (Exception e){

            }
        });

        languageIds.forEach(roleId -> {
            try {
                saveEntryForLanguage(userEmail, roleId);
            } catch (Exception e) {

            }
        });

        skillIds.forEach(roleId -> {
            try {
                saveEntryForSkill(userEmail, roleId);
            } catch (Exception e) {

            }
        });
    }

    public void saveProfileUrls(EditEmployeeDto editEmployeeDto) throws Exception{
        log.info("{}",editEmployeeDto);
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
                    documentUrl = DocumentUrl.builder().build();
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
             skills = Skills.builder()
                            .skill(skill)
                            .userEmail(userEmail)
                            .build();
             skillsManager.save(skills);
        }
    }

    private void saveEntryForLanguage(String userEmail,String language) throws Exception{
        Languages languages = languagesManager.getByUserEmailAndRoleId(userEmail,language);
        if(languages == null){
            languages = Languages.builder()
                                .userEmail(userEmail)
                                .language(language)
                                .build();
            languagesManager.save(languages);
        }

    }

    private void saveEntryForInterest(String userEmail,String interest) throws Exception{
        Interests interests = interestsManager.getByUserEmailAndRoleId(userEmail,interest);
        if(interests == null){
            interests = Interests.builder()
                                 .userEmail(userEmail)
                                 .interest(interest)
                                 .build();
            interestsManager.save(interests);
        }
    }

    public void updateFrequency(EmployeeData employeeData,boolean isSearched) throws Exception{
        if(isSearched) {
            employeeData.setFrequency(employeeData.getFrequency());
            employeeDataManager.save(employeeData);
        }

    }
}
