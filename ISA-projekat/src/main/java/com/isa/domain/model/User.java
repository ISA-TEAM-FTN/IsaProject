package com.isa.domain.model;

import com.isa.enums.Gender;
import com.isa.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The user entity representing all users of the system.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity {

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String phone;

    @NotNull
    private boolean firstLogin;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String personalId;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private String occupation;

    private String occupationInfo;

    @ManyToOne
    private CenterAccount centerAccount;
    
    

}