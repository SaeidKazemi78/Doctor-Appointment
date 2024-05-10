package com.corporation.doctorsappointment.controller.dto;

import com.corporation.doctorsappointment.domain.PatientEntity;
import lombok.Data;

@Data
public class PatientDTO {
    private Long id;

    private String fullName;

    private String phoneNumber;

    public static PatientDTO toDTO(PatientEntity patientEntity) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patientEntity.getId());
        patientDTO.setFullName(patientEntity.getFullName());
        patientDTO.setPhoneNumber(patientEntity.getPhoneNumber());
        return patientDTO;
    }

}
