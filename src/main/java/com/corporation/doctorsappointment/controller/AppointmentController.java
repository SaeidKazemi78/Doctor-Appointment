package com.corporation.doctorsappointment.controller;


import com.corporation.doctorsappointment.controller.dto.AppointmentDTO;
import com.corporation.doctorsappointment.controller.dto.AppointmentRequestDTO;
import com.corporation.doctorsappointment.controller.dto.PatientAppointmentRequestDTO;
import com.corporation.doctorsappointment.service.AppointmentService;
import com.corporation.doctorsappointment.service.exceptions.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add-open-times")
    public ResponseEntity<Void> addOpenTimes(@RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {
        appointmentService.addOpenTimes(appointmentRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read-not-taken-appointments")
    public ResponseEntity<List<AppointmentDTO>> readNotTakenAppointments() {
        return ResponseEntity.ok(appointmentService.readNotTakenAppointments());
    }

    @DeleteMapping("/delete-appointments/{id}")
    public ResponseEntity<Void> deleteAppointments(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read-all-open-appointments/{date}")
    public ResponseEntity<List<AppointmentDTO>> readAllOpenAppointments(@PathVariable LocalDate date) {
        return ResponseEntity.ok(appointmentService.readAllOpenAppointments(date));
    }

    @PostMapping("/take-appointment")
    public ResponseEntity<Void> takeAnAppointment(@Valid @RequestBody PatientAppointmentRequestDTO appointmentRequestDTO) {
        appointmentService.takeAnAppointment(appointmentRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read-own-appointments/{phoneNumber}")
    public ResponseEntity<List<AppointmentDTO>> readPatientSpecificAppointment(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(appointmentService.readAllAppointmentsForPatients(phoneNumber));
    }

}
