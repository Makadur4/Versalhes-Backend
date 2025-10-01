package br.com.versalhes.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Long obterClienteId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) { return null; }
        if(!authentication.getCredentials().equals("cliente")) { return null; }

        return (Long) authentication.getPrincipal();
    }

    public static Long obterUsuarioId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) { return null; }
        if(!authentication.getCredentials().equals("usuario")) { return null; }

        return (Long) authentication.getPrincipal();
    }
}
