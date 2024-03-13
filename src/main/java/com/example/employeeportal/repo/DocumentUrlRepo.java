package com.example.employeeportal.repo;

import com.example.employeeportal.model.DocumentUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentUrlRepo extends JpaRepository<DocumentUrl,String> {

    @Query(value="Select d.document_name, d.url FROM document_url_temp d WHERE d.user_email = ?1", nativeQuery = true)
    List<Object> findAllByUserEmail(String userEmail);

    DocumentUrl findFirstByUserEmailAndDocumentName(String userEmail, String name) throws Exception;

    void deleteAllByUserEmail(String userEmail) throws Exception;
}
