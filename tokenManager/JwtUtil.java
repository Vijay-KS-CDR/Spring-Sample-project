package com.example.Notes.tokenManager;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private  final String SCRT="Vijay Anish both of them are brothers who have a age diff of 9years ";
    private final long EXPR=1000*60*3;
    private Key secretkey= Keys.hmacShaKeyFor(SCRT.getBytes(StandardCharsets.UTF_8));

    public String generate(String name) {
        return Jwts.builder()
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis())+EXPR))
                .signWith(secretkey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validation(String token){
        try{
            extractName(token);
            return true;

        }catch(JwtException exception){
            return false;
        }
    }
}
