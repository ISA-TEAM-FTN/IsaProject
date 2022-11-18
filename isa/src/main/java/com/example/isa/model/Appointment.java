package com.example.isa.model;

import java.time.Instant;

public class Appointment extends Entity {

    private CenterAccount centerAccount; //center account has more appointments manyToOne

    private Instant dateAndTime;

    private int duration;

    private User adminOfCenter;

    private User patient;

    private Poll poll;

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public CenterAccount getCenterAccount() {
        return centerAccount;
    }

    public void setCenterAccount(CenterAccount centerAccount) {
        this.centerAccount = centerAccount;
    }

    public Instant getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Instant dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User getAdminOfCenter() {
        return adminOfCenter;
    }

    public void setAdminOfCenter(User adminOfCenter) {
        this.adminOfCenter = adminOfCenter;
    }
}
