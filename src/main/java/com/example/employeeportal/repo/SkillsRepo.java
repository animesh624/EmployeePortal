package com.example.employeeportal.repo;

import com.example.employeeportal.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepo extends JpaRepository<Skills,String> {

    List<Skills> findAllByUserEmail(String userEmail);
}
