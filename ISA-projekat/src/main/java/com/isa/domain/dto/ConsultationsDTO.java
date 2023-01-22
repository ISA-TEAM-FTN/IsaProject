package com.isa.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultationsDTO {

    private String date;
    private String duration;
    private Long pharmacistsId;
    private Long patientId;
}
