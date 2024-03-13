package com.example.employeeportal.manager.impl;


import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.model.ManagerReportee;
import com.example.employeeportal.repo.ManagerReporteeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerReporteeManagerImpl extends GenericManagerImpl<ManagerReportee,String> implements ManagerReporteeManager {
    ManagerReporteeRepo managerReporteeRepo;

    @Autowired
    public  void setManagerReporteeRepo(ManagerReporteeRepo managerReporteeRepo){
        this.managerReporteeRepo = managerReporteeRepo;
        this.repository = managerReporteeRepo;
    }

    @Override
    public List<ManagerReportee> getAllByManagerEmail(String managerEmail) throws Exception{
        return managerReporteeRepo.getAllByManagerEmail(managerEmail);
    }

    @Override
    public ManagerReportee getByReporteeAndManager(String reporteeEmail, String managerEmail) throws Exception{
        return managerReporteeRepo.getFirstByReporteeEmailAndManagerEmail(reporteeEmail,managerEmail);
    }
}
