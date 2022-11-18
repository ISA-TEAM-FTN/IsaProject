package com.example.isa.model;

import com.example.isa.model.enums.LoyaltyProgramCategory;

public class LoyaltyProgram extends Entity{

    //Na osnovu loyalty programa ce biti neki popust?
    private LoyaltyProgramCategory category;

    private int points;


    public LoyaltyProgramCategory getCategory() {
        return category;
    }

    public void setCategory(LoyaltyProgramCategory category) {
        this.category = category;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
