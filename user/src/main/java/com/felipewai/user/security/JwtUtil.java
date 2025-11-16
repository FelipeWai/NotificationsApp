package com.felipewai.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "iiiijebefwibweioibewfibqfbiqwiefbqb2807f";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public String generateToken(Long userId, String email) {
        return JWT.create()
                .withSubject(email)
                .withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public String getEmailFromToken(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

    public Long getIdFromToken(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaim("id").asLong();
    }

    public boolean isValidToken(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
