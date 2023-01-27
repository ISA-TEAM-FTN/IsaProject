package com.isa.domain.model;

import com.isa.enums.EquipmentType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Equipment  extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    private double amount;

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
