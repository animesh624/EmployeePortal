package com.example.employeeportal.repo;

import com.example.employeeportal.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepo extends JpaRepository<Languages,String> {


    @Query(value="SELECT l.language FROM languages_temp l WHERE l.user_email = ?1", nativeQuery = true)
    List<String> getAllRoleIdByUserEmail(String userEmail) throws Exception;

    Languages getFirstByUserEmailAndLanguage(String userEmail, String roleId) throws Exception;

    void deleteAllByUserEmail(String userEmail) throws Exception;
}
