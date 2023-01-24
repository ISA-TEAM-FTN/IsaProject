package com.isa.domain.model;

import com.isa.enums.BloodType;

import javax.persistence.Entity;

@Entity
public class Poll extends AbstractEntity {

    private BloodType bloodType;

    private boolean patientArrived;

    //da li je odbijen
    private boolean deniedType;

    //razlog za odbijanje pacijenta
    private String reasonForDenied;

    private double amountOfBlood;

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public boolean isPatientArrived() {
        return patientArrived;
    }

    public void setPatientArrived(boolean patientArrived) {
        this.patientArrived = patientArrived;
    }

    public boolean isDeniedType() {
        return deniedType;
    }

    public void setDeniedType(boolean deniedType) {
        this.deniedType = deniedType;
    }

    public String getReasonForDenied() {
        return reasonForDenied;
    }

    public void setReasonForDenied(String reasonForDenied) {
        this.reasonForDenied = reasonForDenied;
    }

    public double getAmountOfBlood() {
        return amountOfBlood;
    }

    public void setAmountOfBlood(double amountOfBlood) {
        this.amountOfBlood = amountOfBlood;
    }
}
