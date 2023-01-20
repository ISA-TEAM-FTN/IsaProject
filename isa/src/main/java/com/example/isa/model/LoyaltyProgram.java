package com.example.isa.model;

import com.example.isa.model.enums.LoyaltyProgramCategory;

import javax.persistence.*;

@Entity
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //Na osnovu loyalty programa ce biti neki popust?
    @Enumerated(value = EnumType.STRING)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
