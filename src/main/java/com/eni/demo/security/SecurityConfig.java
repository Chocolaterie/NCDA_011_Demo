package com.eni.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    public EniAuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        .anyRequest().permitAll() // Autorise le reste des routes
                )
                .formLogin(form -> form
                        .loginPage("/login") // On dit à Spring d'utiliser NOTRE page login.html
                        .loginProcessingUrl("/perform_login") // URL où envoyer les identifiants
                        .defaultSuccessUrl("/", true) // Redirige après connexion
                        .failureUrl("/login?error=true") // En cas d'échec
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/")); // Gère la déconnexion

        // Configurer l'authentification de Spring Security
        http.httpBasic(withDefaults());

        http.csrf(csrf -> {
            csrf.disable();
        });


        return http.build();
    }

    /**
     * Surcharger comment on se connecte (le code qui test la connexion)
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(authenticationProvider));
    }
}
