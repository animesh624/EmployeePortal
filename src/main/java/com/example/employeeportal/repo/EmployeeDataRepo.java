package com.example.employeeportal.repo;

import com.example.employeeportal.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDataRepo extends JpaRepository<EmployeeData, String> {

    EmployeeData findFirstByUserName(String userName) throws Exception;

    EmployeeData findFirstByEmpCode(String empCode) throws Exception;

//    List<EmployeeData> findTop5ByFullNameContainingOrderByFrequencyDesc(String keyword);
//
//    List<EmployeeData> findTop5ByUserNameContainingOrderByFrequencyDesc(String keyword);
//
//    List<EmployeeData> findTop5ByInterestContainingOrderByFrequencyDesc(String keyword);
}
