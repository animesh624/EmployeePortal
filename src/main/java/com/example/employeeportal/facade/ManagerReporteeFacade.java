package com.example.employeeportal.facade;


import com.example.employeeportal.dto.GetNeighboursDto;
import com.example.employeeportal.dto.TreeNodeDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.ManagerReportee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ManagerReporteeFacade {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    ManagerReporteeManager managerReporteeManager;

    public List<TreeNodeDto> getDetailsForEmployeeReportee(GetNeighboursDto getNeighboursDto) throws Exception{
        List<TreeNodeDto> finalList = new ArrayList<>();
        List<ManagerReportee> allReporteeOfManager = managerReporteeManager.getAllByManagerEmail(getNeighboursDto.getUserEmail());
        allReporteeOfManager.forEach(value -> {
            try {
                TreeNodeDto temp = new TreeNodeDto();
                EmployeeData reporteeDetails = employeeDataManager.getEmpCodeDesignationNameByUserEmail(value.getReporteeEmail());
                temp.setUserEmail(reporteeDetails.getUserEmail());
                temp.setFirstName(reporteeDetails.getFirstName());
                temp.setDesignation(reporteeDetails.getDesignation());
                finalList.add(temp);
            }
            catch (Exception e){
                log.info("Exception occured while getting reportee details {}",e);
            }
        });
        return finalList;
    }

    public TreeNodeDto getDetailsForEmployeeManager(GetNeighboursDto getNeighboursDto) throws Exception {
        String managerEmail = employeeDataManager.getManagerEmailByUserEmail(getNeighboursDto.getUserEmail());
        TreeNodeDto temp = new TreeNodeDto();
        EmployeeData managerDetails = employeeDataManager.getEmpCodeDesignationNameByUserEmail(managerEmail);
        if(managerDetails == null) {
            return null;
        }
        temp.setDesignation(managerDetails.getDesignation());
        temp.setUserEmail(managerDetails.getUserEmail());
        temp.setFirstName(managerDetails.getFirstName());
        return temp;
    }

    public TreeNodeDto getDetailsForNode(GetNeighboursDto getNeighboursDto) throws Exception{
        EmployeeData employeeData = employeeDataManager.getByUserEmail(getNeighboursDto.getUserEmail());
        TreeNodeDto treeNodeDto = new TreeNodeDto();
        if(employeeData == null)
            return treeNodeDto;
        treeNodeDto.setUserEmail(employeeData.getUserEmail());
        treeNodeDto.setDesignation(employeeData.getDesignation());
        treeNodeDto.setFirstName(employeeData.getFirstName());
        return treeNodeDto;
    }
}
