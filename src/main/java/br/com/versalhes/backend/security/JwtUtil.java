package br.com.versalhes.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "041cd42b-5847-49b4-b05e-a50fe0eb20b1";
    private static final long EXPIRATION_TIME = 36000000;

    private static Key obterChave() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String gerarToken(String perfil, Long id) {
        return Jwts.builder()
                .claim("id", id)
                .claim("perfil", perfil)
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

    public static Secao obterSecao(String token) {
        Claims claims = validarToken(token);

        String perfil = claims != null ? claims.get("perfil", String.class) : null;
        Long id = claims != null ? claims.get("id", Long.class) : null;

        if(perfil!=null && id != null) {
            return new Secao(perfil, id);
        }

        return null;
    }

    public record Secao(String perfil, Long id) {}
}
