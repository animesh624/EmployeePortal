package com.example.employeeportal.repo;

import com.example.employeeportal.dto.TreeNodeDto;
import com.example.employeeportal.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDataRepo extends JpaRepository<EmployeeData, String> {

    EmployeeData findFirstByUserName(String userName) throws Exception;

    EmployeeData findFirstByEmpCode(String empCode) throws Exception;

    @Query(value="SELECT e.first_name, e.designation, e.emp_code  FROM employee_data_temp e WHERE e.user_name = ?1", nativeQuery = true)
    TreeNodeDto getEmpCodeDesignationNameByUserName(String userName) throws Exception;

    @Query(value="SELECT e.manager_email FROM employee_data_temp e WHERE e.user_name = ?1", nativeQuery = true)
    String getManagerEmailByUserName (String username) throws Exception;

//    List<EmployeeData> findTop5ByFullNameContainingOrderByFrequencyDesc(String keyword);
//
//    List<EmployeeData> findTop5ByUserNameContainingOrderByFrequencyDesc(String keyword);
//
//    List<EmployeeData> findTop5ByInterestContainingOrderByFrequencyDesc(String keyword);
}
