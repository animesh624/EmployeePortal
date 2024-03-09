package com.example.employeeportal.repo;

import com.example.employeeportal.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepo extends JpaRepository<Languages,String> {

    List<Languages> findAllByUserEmail(String userEmail) throws Exception;
}
