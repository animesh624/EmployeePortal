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

    private EmployeeDataManager employeeDataManager;
    private ManagerReporteeManager managerReporteeManager;

    @Autowired
    public ManagerReporteeFacade(EmployeeDataManager employeeDataManager,
                         ManagerReporteeManager managerReporteeManager) {
        this.employeeDataManager = employeeDataManager;
        this.managerReporteeManager = managerReporteeManager;
    }


    public List<TreeNodeDto> getDetailsForEmployeeReportee(GetNeighboursDto getNeighboursDto) throws Exception{
        List<TreeNodeDto> finalList = new ArrayList<>();
        List<ManagerReportee> allReporteeOfManager = managerReporteeManager.getAllByManagerEmail(getNeighboursDto.getUserEmail());
        allReporteeOfManager.forEach(value -> {
            try {

                EmployeeData reporteeDetails = employeeDataManager.getEmpCodeDesignationNameByUserEmail(value.getReporteeEmail());
                TreeNodeDto temp = TreeNodeDto.builder()
                                .userEmail(reporteeDetails.getUserEmail())
                                .firstName(reporteeDetails.getFirstName() + reporteeDetails.getLastName())
                                .designation(reporteeDetails.getDesignation())
                                .profileImageUrl(reporteeDetails.getProfileImageUrl())
                                .build();
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

            EmployeeData managerDetails = employeeDataManager.getEmpCodeDesignationNameByUserEmail(managerEmail);
            if(managerDetails == null)
                return null;
            return TreeNodeDto.builder()
                    .designation(managerDetails.getDesignation())
                    .userEmail(managerDetails.getUserEmail())
                    .firstName(managerDetails.getFirstName() + " " + managerDetails.getLastName())
                    .profileImageUrl(managerDetails.getProfileImageUrl())
                    .build();
    }

    public TreeNodeDto getDetailsForNode(GetNeighboursDto getNeighboursDto) throws Exception{
        EmployeeData employeeData = employeeDataManager.getByUserEmail(getNeighboursDto.getUserEmail());

        if(employeeData == null)
            return TreeNodeDto.builder().build();

        return TreeNodeDto.builder()
                    .userEmail(employeeData.getUserEmail())
                    .designation(employeeData.getDesignation())
                    .firstName(employeeData.getFirstName() + " " + employeeData.getLastName())
                    .profileImageUrl(employeeData.getProfileImageUrl())
                    .build();
    }
}
