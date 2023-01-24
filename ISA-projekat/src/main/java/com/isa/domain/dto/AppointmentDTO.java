package com.isa.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {

    private String date;
    private String duration;
    private Long adminOfCenterId;
}
