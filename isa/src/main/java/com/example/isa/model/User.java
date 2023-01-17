package com.example.isa.model;

import com.example.isa.model.enums.Gender;
import com.example.isa.model.enums.UserType;

public class User extends Entity{
    private String email;
    private String password;
    private String name;
    private String surname;
    private UserType userType;
    private String telephoneNumber;
    private String personalId;
    private Gender gender;
    private String occupation;
    private String occupationInfo;
    private boolean isActive;
    private boolean firstLogin;
    private int penaltyNumber;

    private LoyaltyProgram loyaltyProgram; //Patient (ManyToOne)

    private CenterAccount centerAccount; //Center account has more users, user has one center account (ManyToOne)

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationInfo() {
        return occupationInfo;
    }

    public void setOccupationInfo(String occupationInfo) {
        this.occupationInfo = occupationInfo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public int getPenaltyNumber() {
        return penaltyNumber;
    }

    public void setPenaltyNumber(int penaltyNumber) {
        this.penaltyNumber = penaltyNumber;
    }
}
