package com.tap.backendtap.model;

import javax.persistence.*;

/**
 * Created by Florina on 5/6/2020
 */
@Entity
@Table(name = "users")
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsers;

    @Column
    private String name;

    @Column
    private String password;

    public Authentication() {
    }

    public Authentication(Long idUsers, String name, String password) {
        this.idUsers = idUsers;
        this.name = name;
        this.password = password;
    }

    public Long getIdUsers() {
        return idUsers;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Model{" +
                "idUsers=" + idUsers +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
