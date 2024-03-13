package com.example.employeeportal.repo;

import com.example.employeeportal.model.ManagerReportee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ManagerReporteeRepo extends JpaRepository<ManagerReportee,String> {

    List<ManagerReportee> getAllByManagerEmail(String managerEmail) throws Exception;

    ManagerReportee getFirstByReporteeEmailAndManagerEmail(String reporteeEmail, String managerEmail) throws Exception;

    @Transactional
    void deleteByReporteeEmail(String reporteeEmail) throws Exception;


}
