package com.example.employeeportal.repo;

import com.example.employeeportal.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepo extends JpaRepository<Language,String> {

    List<Language> findAllByUserEmail(String userEmail) throws Exception;
}
