package com.example.employeeportal.repo;

import com.example.employeeportal.model.ManagerReportee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerReporteeRepo extends JpaRepository<ManagerReportee,String> {

    List<ManagerReportee> getAllByManagerEmail(String managerEmail);
}
