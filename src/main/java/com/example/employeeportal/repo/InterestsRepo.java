package com.example.employeeportal.repo;

import com.example.employeeportal.model.Interests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestsRepo extends JpaRepository<Interests,String> {
    List<Interests> findAllByUserEmail(String userEmail);

}
