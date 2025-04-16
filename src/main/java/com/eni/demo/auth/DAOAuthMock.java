package com.eni.demo.auth;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DAOAuthMock implements IDAOAuth {

    List<AuthUser> fakeUsers = new ArrayList<>();

    public DAOAuthMock(){
        fakeUsers.add(new AuthUser("toto@gmail.com", "password"));
        fakeUsers.add(new AuthUser("tata@gmail.com", "test"));
    }

    @Override
    public AuthUser selectUserByEmail(String email) {

        AuthUser foundUser = fakeUsers.stream().filter((user) -> user.getEmail().equals(email)).findFirst().orElse(null);

        return foundUser;
    }
}
