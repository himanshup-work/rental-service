package com.auth_service.utils;

import com.auth_service.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class for JWT token generation, extraction, and validation.
 */
@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtTokenValidity;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a JWT token for the given user.
     *
     * @param user the user for whom the token is generated
     * @return the generated JWT token
     */
    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        return Jwts.builder()
                .subject(user.getEmail())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtTokenValidity))
                .signWith(getSecretKey())
                .compact();
    }

    // Validate the token with user details
    public Boolean validateToken(String token, User user) {
        String username = extractUsername(token);
        return username.equals(user.getEmail()) && !isTokenExpired(token);
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the user ID from the given JWT token.
     *
     * @param token the JWT token
     * @return the extracted user ID
     */
    public String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", String.class));
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    // Extract a specific claim from the token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey()) // Use verifyWith for secure key validation
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
