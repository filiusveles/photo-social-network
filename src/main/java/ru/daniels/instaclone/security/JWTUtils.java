package ru.daniels.instaclone.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTUtils {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.sessionTime}")
    private long SESSION_TIME;

    public String getToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        String sepListOfAuth = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("authorities", sepListOfAuth);
        return createToken(claims, userDetails.getUsername());
    }
    
    public String extractUsernameFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String extractAuthorities(String token){
        return extractClaim(token, claims -> (String) claims.get("authorities"));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SESSION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


}
