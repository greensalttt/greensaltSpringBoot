package com.example.greenspringboot.config;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256비트 시크릿키 자동 생성
    private final long expirationMs = 1000 * 60 * 60 * 2; // 2시간 유효

    // 토큰 발급
    public String generateToken(Integer adminId) {
        return Jwts.builder()
                .setSubject(String.valueOf(adminId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    // 토큰 검증 및 adminId 추출
    public Integer validateTokenAndGetAdminId(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Integer.valueOf(claims.getSubject()); // adminId
        } catch (JwtException e) {
            return null;
        }
    }
}
