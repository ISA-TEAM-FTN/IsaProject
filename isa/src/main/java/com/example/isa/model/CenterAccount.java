package com.example.isa.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class CenterAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne
    private Address address;
    private String description;
    private double averageRating;

    private LocalTime startTime;

    private LocalTime endTime;

    @OneToMany
    private List<User> centerAdmins;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getCenterAdmins() {
        return centerAdmins;
    }

    public void setCenterAdmins(List<User> centerAdmins) {
        this.centerAdmins = centerAdmins;
    }
}
