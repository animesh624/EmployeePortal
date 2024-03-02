package com.example.employeeportal.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Date;

@Component
@Slf4j
public class JWTHelper {
    private String secret;
    private int expiresIn;

    public JWTHelper() {
    }

    @PostConstruct
    public void init() {
        try {
            this.secret = "ac501e4796751142fe3e62b2b67ca1d2e8b095067846ebdc19d28128eaf9e3b91eac87bd7e897519de415b0d754ada0300f79b0106e65edd7879c985e9b131f2";
            this.expiresIn = 86400;
        } catch (Exception var2) {
            log.error("Error initializing constant for token", var2);
        }

    }

    public boolean validateSubject(String token, String subjectKey) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            String subject = creteSubject(subjectKey);
            JWTVerifier verifier = JWT.require(algorithm).withSubject(subject).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt != null;
        } catch (JWTVerificationException var6) {
            log.error("Error while validateSubject {}",var6);
            return false;
        }
    }

    public String generateToken(String tokenSubject, String issuer) {
        String token = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            token = JWT.create().withIssuer(issuer).withSubject(tokenSubject).withIssuedAt(new Date()).withExpiresAt(DateUtil.addSeconds(new Date(), this.expiresIn)).sign(algorithm);
            return token;
        } catch (JWTCreationException var5) {
            log.error("Errro while generating token {}",var5);
            return null;
        }
    }

    public String creteSubject(String subjectKey){
        int laConfigVersion = 0;
        return laConfigVersion +"#"+ subjectKey;
    }
}
