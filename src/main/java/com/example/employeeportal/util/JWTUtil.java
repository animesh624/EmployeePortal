package com.example.employeeportal.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class JWTUtil {

    @Autowired
    JWTHelper jwtHelper;

    public boolean isTokenValid(String securityToken, String subjectKey) {
        return jwtHelper.validateSubject(securityToken, subjectKey);
    }

    public String createJWTToken(String subjectKey, String issuer) {

        if (StringUtils.isEmpty(subjectKey))
            return null;

        String token = null;
        try {
            String tokenSubject = jwtHelper.creteSubject(subjectKey);
            token = jwtHelper.generateToken(tokenSubject, issuer);
        } catch (Exception e) {
            log.error("Error occurred while generating jwt token.");
        }

        return token;

    }
}
