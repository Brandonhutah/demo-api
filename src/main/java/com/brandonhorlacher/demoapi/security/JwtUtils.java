package com.brandonhorlacher.demoapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.brandonhorlacher.demoapi.users.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    Algorithm algorithm;
    JWTVerifier verifier;

    JwtUtils(@Value("${jwt.signing-key}") String signing_key, @Value("${jwt.issuer}") String issuer, @Value("${jwt.subject}") String subject) {
        this.algorithm = Algorithm.HMAC512(signing_key);
        this.verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withSubject(subject)
                .build();
    }

    public boolean isValidToken(String token) {
        try {
            verifier.verify(token);

            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    public User extractUser(String token) {
        DecodedJWT jwt = verifier.verify(token);

        return new Gson().fromJson(jwt.getClaim("user").asString(), User.class);
    }
}
