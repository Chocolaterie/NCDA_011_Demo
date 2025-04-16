package com.eni.demo.auth;

import jakarta.persistence.*;

@Table(name = "UTILISATEURS")
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no_utilisateur")
    private Long noUtilisateur;

    private String email;

    private String password;

    public AuthUser() {
    }

    public AuthUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(Long noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
