package com.eni.demo.auth;

import com.eni.demo.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    IDAOAuth daoAuth;

    public ServiceResponse<AuthUser> login(String email, String password){
        // LA DAO qui recupere la personne avec son mail
        AuthUser authUser = daoAuth.selectUserByEmail(email);

        // Erreur : Couple email/mot de passe
        if (authUser==null || !authUser.getPassword().equals(password)){
            return ServiceResponse.buildResponse("746", "Couple email / mot de passe incorrect", null);
        }

        // Success
        return ServiceResponse.buildResponse("200", "Couple email / mot de passe incorrect", authUser);
    }
}
