package com.isa.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private Long pharmacyId;
    private Long patientId;
    private Long medicineId;
}
