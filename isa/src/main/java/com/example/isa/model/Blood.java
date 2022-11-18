package com.example.isa.model;

import com.example.isa.model.enums.BloodType;

public class Blood extends Entity {

    private BloodType bloodType;
    private double amount;

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
