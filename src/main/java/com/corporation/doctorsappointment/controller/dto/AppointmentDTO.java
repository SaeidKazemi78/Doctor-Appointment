package com.corporation.doctorsappointment.controller.dto;

import com.corporation.doctorsappointment.domain.AppointmentEntity;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean isTakenByPatient;

    private LocalDate createdDate;

    private PatientDTO patientDTO;

    //Instead of this way , we could have used Mapstruct as mapper between Entity and DTO
    public static AppointmentDTO toDTO(AppointmentEntity appointmentEntity) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointmentEntity.getId());
        appointmentDTO.setStartTime(appointmentEntity.getStartTime());
        appointmentDTO.setEndTime(appointmentEntity.getEndTime());
        appointmentDTO.setCreatedDate(appointmentEntity.getCreatedDate());
        appointmentDTO.setIsTakenByPatient(appointmentEntity.getIsTakenByPatient());
        if (!ObjectUtils.isEmpty(appointmentEntity.getPatientEntity())) {
            appointmentDTO.setPatientDTO(PatientDTO.toDTO(appointmentEntity.getPatientEntity()));
        }
        return appointmentDTO;
    }
}
