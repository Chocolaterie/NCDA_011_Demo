package com.eni.demo.auth;

import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<AuthUser, Long> {

    AuthUser findByEmail(String email);
}
