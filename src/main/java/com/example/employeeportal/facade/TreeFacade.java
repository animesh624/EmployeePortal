package com.example.employeeportal.facade;


import com.example.employeeportal.manager.DocumentUrlManager;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.InterestsManager;
import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.ManagerReportee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TreeFacade {

    private EmployeeDataManager employeeDataManager;
    private ManagerReporteeManager managerReporteeManager;
    private LanguagesManager languagesManager;
    private SkillsManager skillsManager;
    private InterestsManager interestsManager;
    private UserDataManager userDataManager;

    private DocumentUrlManager documentUrlManager;

    @Autowired
    public TreeFacade(ManagerReporteeManager managerReporteeManager,
                      EmployeeDataManager employeeDataManager,
                      LanguagesManager languagesManager,
                      SkillsManager skillsManager,
                      InterestsManager interestsManager,
                      UserDataManager userDataManager,
                      DocumentUrlManager documentUrlManager){
        this.employeeDataManager = employeeDataManager;
        this.managerReporteeManager = managerReporteeManager;
        this.interestsManager = interestsManager;
        this.languagesManager = languagesManager;
        this.skillsManager = skillsManager;
        this.userDataManager = userDataManager;
        this.documentUrlManager = documentUrlManager;
    }

    public void handleManagerChange(EmployeeData employeeData,String newManagerEmail) throws Exception{
        String previousManagerEmail = employeeData.getManagerEmail();
        EmployeeData newManager = employeeDataManager.getByUserEmail(newManagerEmail);

        removeAllRelationShip(newManager,false);

        // removing entry of employee and its previousManager.
        managerReporteeManager.deleteByReporteeEmail(employeeData.getUserEmail());

        buildRelationShip(employeeData,newManagerEmail);
        buildRelationShip(newManager, previousManagerEmail);

    }

    public void removeAllRelationShip(EmployeeData employeeData, boolean isDeleteRequired) throws Exception{
        String managerEmail = employeeData.getManagerEmail();
        if(managerEmail == null){
            throw new Exception("Cannot delete senior most employee this way");
        }
        managerReporteeManager.deleteByReporteeEmail(employeeData.getUserEmail());

        List<ManagerReportee> reportees = managerReporteeManager.getAllByManagerEmail(employeeData.getUserEmail());
        changeManagerOfReportees(reportees,managerEmail);

        if(isDeleteRequired){
            deleteEntryOfEmployee(employeeData);
        }

    }


    // in this we pass node(employeeData) and its parentEmail (managerEmail) to build relationShip.
    private void buildRelationShip(EmployeeData employeeData, String managerEmail) throws Exception{

       employeeData.setManagerEmail(managerEmail);

       ManagerReportee managerReportee = ManagerReportee.builder()
               .reporteeEmail(employeeData.getUserEmail())
               .managerEmail(managerEmail)
               .build();

       managerReporteeManager.save(managerReportee);
    }

    private void changeManagerOfReportees(List<ManagerReportee> reportees, String managerEmail) throws Exception{
        reportees.forEach(reportee -> {
            try {
                EmployeeData employeeData = employeeDataManager.getByUserEmail(reportee.getReporteeEmail());
                employeeData.setManagerEmail(managerEmail);
                employeeDataManager.save(employeeData);

                reportee.setManagerEmail(managerEmail);
                managerReporteeManager.save(reportee);
            }catch (Exception e){
                log.info("Exception occured while changing reportee {}",e);
            }
        });
    }

    private void deleteEntryOfEmployee(EmployeeData employeeData) throws Exception{
        String userEmail = employeeData.getUserEmail();

        languagesManager.deleteAllByUserEmail(userEmail);
        skillsManager.deleteAllByUserEmail(userEmail);
        interestsManager.deleteAllByUserEmail(userEmail);
        documentUrlManager.deleteAllByUserEmail(userEmail);
        employeeDataManager.delete(employeeData);
        userDataManager.deleteByUserEmail(userEmail);
    }
}
