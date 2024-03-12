package com.example.employeeportal.facade;

import com.example.employeeportal.dto.UploadDocumentDto;
import com.example.employeeportal.manager.DocumentUrlManager;
import com.example.employeeportal.manager.UserRoleMasterManager;
import com.example.employeeportal.model.DocumentUrl;
import com.example.employeeportal.model.UserRoleMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentUrlFacade {

    @Autowired
    DocumentUrlManager documentUrlManager;

    @Autowired
    UserRoleMasterManager userRoleMasterManager;
    public void saveDocumentUrlData (UploadDocumentDto uploadDocumentDto, String fileUrl) throws Exception{
        if(fileUrl == null){
            throw new Exception("Error while saving file to S3");
        }
        DocumentUrl documentUrl = documentUrlManager.getByUserEmailAndName(uploadDocumentDto.getUserEmail(),uploadDocumentDto.getFileName());
        if(documentUrl != null){
            documentUrl.setUrl(fileUrl);
        }
        documentUrl = buildDocumentUrlRequest(uploadDocumentDto, fileUrl);
        documentUrlManager.save(documentUrl);
    }

    private DocumentUrl buildDocumentUrlRequest(UploadDocumentDto uploadDocumentDto,String fileUrl){
        return DocumentUrl.builder()
                .userEmail(uploadDocumentDto.getUserEmail())
                .documentName(uploadDocumentDto.getFileName())
                .url(fileUrl)
                .build();
    }
}
