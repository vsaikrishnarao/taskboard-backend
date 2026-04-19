package com.taskboard.taskboardbackend.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret_key}")
    private String secret_key;

    private SecretKey getSigningKey(){
       return Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getClaim(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(String email){
        Instant now= Instant.now();
        return Jwts.builder()
                .signWith(getSigningKey())
                .subject(email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(86400)))
                .compact();
    }


    public String extractToken(String token){
            return getClaim(token).getSubject();
    }
}
