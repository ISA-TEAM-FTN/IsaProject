package com.isa.domain.dto;


import com.isa.domain.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {

    private String date;
    private String duration;
    private String description;
    private Long doctorId;
    private Long patientId;
}
