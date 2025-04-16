package com.eni.demo.auth;

public interface IDAOAuth {

    AuthUser selectUserByEmail(String email);
}
