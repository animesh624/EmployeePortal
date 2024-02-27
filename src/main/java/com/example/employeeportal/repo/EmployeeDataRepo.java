package com.example.employeeportal.repo;

import com.example.employeeportal.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeDataRepo extends JpaRepository<EmployeeData, String> {

    EmployeeData findFirstByUserName(String userName) throws Exception;
}
