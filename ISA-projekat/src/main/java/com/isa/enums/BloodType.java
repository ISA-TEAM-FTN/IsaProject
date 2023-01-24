package com.isa.enums;

public enum BloodType {
    ZERO("0"), A("A"), B("B"), AB("AB");

    private final String text;

    BloodType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
