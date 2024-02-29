package com.example.employeeportal.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

    private static ZoneOffset offset;
    private String secret;
    private int expiresIn;

    public JWTUtil() {
    }

    @PostConstruct
    public void init() {
        try {
            this.secret = "ac501e4796751142fe3e62b2b67ca1d2e8b095067846ebdc19d28128eaf9e3b91eac87bd7e897519de415b0d754ada0300f79b0106e65edd7879c985e9b131f2";
            this.expiresIn = 86400;
        } catch (Exception var2) {
            log.error("Empty required constants for JWT", var2);
        }
    }

    public String generateToken(String tokenSubject, String issuer) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            token = JWT.create().withIssuer(issuer).withSubject(tokenSubject).withIssuedAt(new Date()).withExpiresAt(DateUtil.addSeconds(new Date(), this.expiresIn)).sign(algorithm);
            return token;
        } catch (JWTCreationException var5) {
            log.error("Could not generate token.. returning null");
            return null;
        }
    }
}
