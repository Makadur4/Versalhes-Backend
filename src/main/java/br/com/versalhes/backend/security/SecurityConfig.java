package br.com.versalhes.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/apoio/**").permitAll()
                        .requestMatchers("/avaliacao/obter-avaliacoes-perfume/**").permitAll()
                        .requestMatchers("/cliente/incluir-cliente").permitAll()
                        .requestMatchers("/cliente/validar-cliente").permitAll()
                        .requestMatchers("/cliente/solicitar-recuperacao-senha").permitAll()
                        .requestMatchers("/cliente/alterar-senha").permitAll()
                        .requestMatchers("/perfume/obter-perfumes-avaliacao").permitAll()
                        .requestMatchers("/perfume/obter-perfumes-venda").permitAll()
                        .requestMatchers("/perfume/obter-perfume/**").permitAll()
                        .requestMatchers("/perfume/obter-imagem/**").permitAll()
                        .requestMatchers("/usuario/**").permitAll()
                        //.requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
