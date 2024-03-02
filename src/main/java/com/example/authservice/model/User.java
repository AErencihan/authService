package com.example.authservice.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.CommandLineRunner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "myUser")
@Data
public class User extends BaseEntity{

    @Column(unique = true)
    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;





}
