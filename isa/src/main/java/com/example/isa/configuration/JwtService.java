package com.example.isa.configuration;

import com.example.isa.model.enums.UserTypes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final String ROLE = "role";
    private static final String SECRET_KEY = "jdiasji23";

    public String createAuthenticationToken(long userId, Instant expirationDate, UserTypes roleNames) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim(ROLE, roleNames)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setExpiration(Date.from(expirationDate))
                .compact();
    }

    public String createRefreshToken(long userId, Instant expirationDate) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setExpiration(Date.from(expirationDate))
                .compact();
    }

    public long getUserId(String token) {
        final Claims claims = getJwtClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    public UserTypes getRole(String token) {
        return UserTypes.valueOf(getClaim(token, ROLE, String.class));
    }

    private <T> T getClaim(String token, String claim, Class<T> claimClass) {
        final Claims claims = getJwtClaims(token);
        return claims.get(claim, claimClass);
    }

    public Claims getJwtClaims(String refreshToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(refreshToken)
                .getBody();
    }
}
