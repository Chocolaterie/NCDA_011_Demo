package com.eni.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("jpa")
@Component
public class DAOAuthJPA implements IDAOAuth {

    AuthRepository authRepository;

    public DAOAuthJPA(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public AuthUser selectUserByEmail(String email) {
        return authRepository.findByEmail(email);
    }
}