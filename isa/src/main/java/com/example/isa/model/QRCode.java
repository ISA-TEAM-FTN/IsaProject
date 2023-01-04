package com.example.isa.model;

import com.example.isa.model.enums.QRCodeStatus;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Appointment appointment;

    private QRCodeStatus qrCodeStatus;

    private Instant dateOfCreation;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public QRCodeStatus getQrCodeStatus() {
        return qrCodeStatus;
    }

    public void setQrCodeStatus(QRCodeStatus qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    public Instant getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Instant dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
