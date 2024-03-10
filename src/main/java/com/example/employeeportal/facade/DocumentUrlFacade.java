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
    public void builDocumentUrlData (UploadDocumentDto uploadDocumentDto, String fileUrl) throws Exception{
        if(fileUrl == null){
            return ;
        }
        DocumentUrl documentUrl = new DocumentUrl();
        documentUrl.setDocumentName(uploadDocumentDto.getFileName());
        documentUrl.setUrl(fileUrl);
        documentUrl.setUserEmail(uploadDocumentDto.getUserEmail());
        documentUrlManager.save(documentUrl);
    }
}
