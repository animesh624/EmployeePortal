package com.example.employeeportal.repo;

import com.example.employeeportal.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepo extends JpaRepository<Skills,String> {

    @Query(value="SELECT s.skill FROM skills_temp s WHERE s.user_email = ?1", nativeQuery = true)
    List<String> getAllRoleIdByUserEmail(String userEmail) throws Exception;
}
