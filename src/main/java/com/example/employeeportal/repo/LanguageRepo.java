package com.example.employeeportal.repo;

import com.example.employeeportal.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepo extends JpaRepository<Languages,String> {

    List<Languages> findAllByUserEmail(String userEmail) throws Exception;
}
