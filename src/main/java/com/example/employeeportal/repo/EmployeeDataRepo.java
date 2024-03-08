package com.example.employeeportal.repo;

import com.example.employeeportal.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDataRepo extends JpaRepository<EmployeeData, String> {

    EmployeeData findFirstByUserEmail(String userEmail) throws Exception;

    EmployeeData findFirstByEmpCode(String empCode) throws Exception;

    @Query(value="SELECT e.*  FROM employee_data_temp e WHERE e.user_email = ?1 LIMIT 1", nativeQuery = true)
    EmployeeData getEmpCodeDesignationNameByUserEmail(String userEmail) throws Exception;

    @Query(value="SELECT e.manager_email FROM employee_data_temp e WHERE e.user_email = ?1", nativeQuery = true)
    String getManagerEmailByUserEmail (String userEmail) throws Exception;

    @Query(value = "Select e.first_name, e.user_email, e.designation FROM employee_data_temp e WHERE e.user_email LIKE %?%",nativeQuery = true)
    EmployeeData searchEmployeeByEmail(String userEmail) throws Exception;

    @Query(value = "Select e.first_name, e.user_email, e.designation FROM employee_data_temp e WHERE e.designation LIKE %?%",nativeQuery = true)
    EmployeeData searchEmployeeByDesignation(String designation) throws Exception;

    @Query(value = "Select e.first_name, e.user_email, e.designation FROM employee_data_temp e WHERE e.first_name LIKE %?% OR e.last_name LIKE %?%",nativeQuery = true)
    EmployeeData searchEmployeeByName(String Name) throws Exception;

}
