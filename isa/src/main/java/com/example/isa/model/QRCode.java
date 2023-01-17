package com.example.isa.model;

import com.example.isa.model.enums.QRCodeStatus;

import java.time.Instant;

public class QRCode extends Entity{

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
}
