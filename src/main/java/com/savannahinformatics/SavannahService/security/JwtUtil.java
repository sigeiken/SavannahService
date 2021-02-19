package com.savannahinformatics.SavannahService.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

@Service
public class JwtUtil {
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.TOKEN_SECRET)
                .setExpiration(from(Instant.now().plusMillis(SecurityConstants.EXPIRATION_TIME)))
                .compact();
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.TOKEN_SECRET)
                .setExpiration(from(Instant.now().plusMillis(SecurityConstants.EXPIRATION_TIME)))
                .compact();
    }

    public boolean validateToken(String jwt) {
        parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(SecurityConstants.TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return SecurityConstants.EXPIRATION_TIME;
    }
}
