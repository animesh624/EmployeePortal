package com.example.employeeportal.repo;

import com.example.employeeportal.model.Interests;
import com.example.employeeportal.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InterestsRepo extends JpaRepository<Interests,String> {

    @Query(value="SELECT i.interest FROM interests_temp i WHERE i.user_email = ?1", nativeQuery = true)
    List<String> getAllRoleIdByUserEmail(String userEmail);

    Interests getFirstByUserEmailAndInterest(String userEmail, String roleId) throws Exception;

    @Transactional
    void deleteAllByUserEmail(String userEmail) throws Exception;

    @Query(value = "SELECT DISTINCT i.user_email " +
            "FROM interests_temp i " +
            "INNER JOIN user_role_master_temp urm ON i.interest = urm.role_id " +
            "WHERE urm.name = LIKE %?1%", nativeQuery = true)
    List<String> getUserEmailByInterest(String interest) throws Exception;

}
