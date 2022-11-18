package com.example.isa.model;

import com.example.isa.model.enums.BloodType;

public class Poll extends Entity{

    private BloodType bloodType;

    private boolean patientArrived;

    //da li je odbijen
    private boolean deniedType;

    //razlog za odbijanje pacijenta
    private String reasonForDenied;

    private double amountOfBlood;



}
