package com.corporation.doctorsappointment.controller.dto;

import lombok.Data;

@Data
public class PatientAppointmentRequestDTO {

    private Long appointmentId;

    private String fullName;

    private String phoneNumber;


}
