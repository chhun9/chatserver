package com.test.chat.component;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${spring.jwt.secret}")
    private String secretKey;
    private Long tokenValidMiliSecond = 1000L * 60 * 60;

    public String generateToken(String name) {
        Date now = new Date();
        return Jwts.builder()
                .setId(name)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMiliSecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserNameFromJwt(String jwt) {
        return getClaims(jwt).getBody().getId();
    }

    public boolean validateToken(String jwt) {
        return this.getClaims(jwt) != null;
    }

    private Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
        } catch (SignatureException e) {
            System.out.println("invalid jwt signature");
            throw e;
        } catch (MalformedJwtException e) {
            System.out.println("invalid jwt token");
            throw e;
        } catch (ExpiredJwtException e) {
            System.out.println("expired jwt token");
            throw e;
        } catch (UnsupportedJwtException e) {
            System.out.println("unsupported jwt token");
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println("jwt claims string is empty");
            throw e;
        }
    }
}
