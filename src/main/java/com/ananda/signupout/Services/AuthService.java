package com.ananda.signupout.Services;

import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    private static String secretKey = "yourSecretKeyddddddddddddddddddddddddddddddddd";

    public String generateToken(String email) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);

            String token = Jwts.builder()
                    .setSubject(email)
                    .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
                    .compact();

            return token;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void verifyToken()
    {
        
    }


}
