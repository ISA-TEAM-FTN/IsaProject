package com.isa.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Appointment extends AbstractEntity {

    @ManyToOne
    private CenterAccount centerAccount; //center account has more appointments manyToOne

    private Instant dateAndTime;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User adminOfCenter;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
}
