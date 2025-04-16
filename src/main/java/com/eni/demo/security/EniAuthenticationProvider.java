package com.eni.demo.security;

import com.eni.demo.auth.AuthService;
import com.eni.demo.auth.AuthUser;
import com.eni.demo.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class EniAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Les récupérer les informations saisies
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Appel la BLL/Manager/Metier/Service
        ServiceResponse<AuthUser> serviceResponse = authService.login(email, password);

        // Tester l'algo de connexion
        if (!serviceResponse.code.equals("200")){
            throw new UsernameNotFoundException(serviceResponse.message);
        }

        // TODO: Tu doit avoir ton propre user detail (héritage)
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getUsername() {
                return email;
            }
        };

        // Ca a marché,
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
