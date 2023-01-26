package com.isa.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Appointment extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "center_account_id")
    private CenterAccount centerAccount; //center account has more appointments manyToOne

    private Instant dateAndTime;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @OneToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    private boolean completedAppointment;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("centerAccount", centerAccount)
                .append("dateAndTime", dateAndTime)
                .append("duration", duration)
                .append("admin", admin)
                .append("patient", patient)
                .append("poll", poll)
                .append("completedAppointment", completedAppointment)
                .toString();
    }
}
