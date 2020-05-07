package com.tap.backendtap.model;

import javax.persistence.*;

/**
 * Created by Florina on 5/6/2020
 */
@Entity
@Table(name = "USERS")
public class Authentication {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column
    private String username;

    @Column
    private String password;

    public Authentication() {
    }

    public Authentication(Long idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "idUsers=" + idUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
