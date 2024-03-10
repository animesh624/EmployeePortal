package com.example.employeeportal.repo;

import com.example.employeeportal.model.Interests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestsRepo extends JpaRepository<Interests,String> {

    @Query(value="SELECT i.interest FROM interests_temp i WHERE i.user_email = ?1", nativeQuery = true)
    List<String> getAllRoleIdByUserEmail(String userEmail);

}
