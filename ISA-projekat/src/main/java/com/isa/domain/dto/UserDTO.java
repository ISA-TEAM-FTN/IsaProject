package com.isa.domain.dto;

import com.isa.domain.model.User;
import com.isa.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phone;
    private boolean firstLogin;
    private String personalId;
    private String gender;
    private String occupation;
    private String occupationInfo;


}
