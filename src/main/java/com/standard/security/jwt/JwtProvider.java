package com.standard.security.jwt;

import com.standard.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtProvider {

    private final String secretKey;
    private final long tokenExpiration;
    private final long refreshTokenExpiration;

    public JwtProvider(@Value("${application.security.jwt.secret-key}") String secretKey,
                      @Value("${application.security.jwt.token.expiration}") long tokenExpiration,
                      @Value("${application.security.jwt.refresh-token.expiration}") long refreshTokenExpiration) {
        this.secretKey = secretKey;
        this.tokenExpiration = tokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public String generateToken(UserDetailsImpl userDetails, boolean isRefreshToken) {
        long expiration = isRefreshToken ? refreshTokenExpiration : tokenExpiration;

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", userDetails.getUser().getId())
                .claim("email", userDetails.getUser().getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();

    }

//    public String generateRefreshToken(UserDetailsImpl userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
//                .compact();
//
//    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

//    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public String getUsernameFromToken(String token) {
        Claims claims = extractAllClaims(token);
        if (claims != null && isTokenExpired(claims)) {
            return claims.getSubject();
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT token compact of handler are invalid");
        }

        return false;
    }

}
