package br.com.versalhes.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "041cd42b-5847-49b4-b05e-a50fe0eb20b1";
    private static final long EXPIRATION_TIME = 3600000;

    private static Key obterChave() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String gerarToken(Long id) {
        return Jwts.builder()
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(obterChave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims  validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(obterChave())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    public static Long obterId(String token) {
        Claims claims = validarToken(token);
        return claims != null ? claims.get("id", Long.class) : null;
    }
}
