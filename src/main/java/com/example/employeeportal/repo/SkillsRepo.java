package com.example.employeeportal.repo;

import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SkillsRepo extends JpaRepository<Skills,String> {

    @Query(value="SELECT s.skill FROM skills_temp s WHERE s.user_email = ?1", nativeQuery = true)
    List<String> getAllRoleIdByUserEmail(String userEmail) throws Exception;

    Skills findFirstByUserEmailAndSkill(String userEmail, String roleId) throws Exception;

    @Transactional
    void deleteAllByUserEmail(String userEmail) throws Exception;

    @Query(value = "SELECT DISTINCT st.user_email " +
            "FROM skills_temp st " +
            "INNER JOIN user_role_master_temp urm ON st.skill = urm.role_id " +
            "WHERE urm.name = ?1", nativeQuery = true)
    List<String> getUserEmailBySkill(String skill) throws Exception;
}
