package com.example.employeeportal.manager;

import com.example.employeeportal.model.ManagerReportee;

import java.util.List;

public interface ManagerReporteeManager extends GenericManager<ManagerReportee,String> {

    List<ManagerReportee> getAllByManagerEmail(String managerEmail) throws Exception;
}
