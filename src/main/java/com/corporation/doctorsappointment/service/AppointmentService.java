package com.corporation.doctorsappointment.service;

import com.corporation.doctorsappointment.controller.dto.AppointmentDTO;
import com.corporation.doctorsappointment.controller.dto.AppointmentRequestDTO;
import com.corporation.doctorsappointment.controller.dto.PatientAppointmentRequestDTO;
import com.corporation.doctorsappointment.domain.AppointmentEntity;
import com.corporation.doctorsappointment.domain.PatientEntity;
import com.corporation.doctorsappointment.repository.AppointmentRepository;
import com.corporation.doctorsappointment.repository.PatientRepository;
import com.corporation.doctorsappointment.service.exceptions.BadRequestException;
import com.corporation.doctorsappointment.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Story: Doctor adds open times.
     */
    @Transactional
    public void addOpenTimes(AppointmentRequestDTO appointmentRequestDTO) throws BadRequestException {

        if (appointmentRequestDTO.getEndTime().isBefore(appointmentRequestDTO.getStartTime()))
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "End date can not be sooner than start date !");
        DateUtils.breakDownInto30Minutes(appointmentRequestDTO.getStartTime(), appointmentRequestDTO.getEndTime()).forEach(eachOne -> {
            AppointmentEntity appointmentEntity = new AppointmentEntity();
            appointmentEntity.setStartTime(eachOne.getFirst());
            appointmentEntity.setEndTime(eachOne.getSecond());
            appointmentEntity.setCreatedDate(LocalDate.now());
            appointmentRepository.save(appointmentEntity);
        });
    }

    /**
     * Story: Doctor can view 30 minutes appointments
     */
    public List<AppointmentDTO> readNotTakenAppointments() {
        List<AppointmentEntity> responseDTO;
        responseDTO = appointmentRepository.findAll();
        if (ObjectUtils.isEmpty(responseDTO)) {
            return new ArrayList<>();
        }
        return responseDTO.stream().map(AppointmentDTO::toDTO).collect(Collectors.toList());
    }


    /**
     * Story: Doctor can delete open appointment
     */
    @Transactional
    public void deleteAppointment(Long id) throws BadRequestException {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST.value(), "404 error has occurred !"));
        if (appointmentEntity.getIsTakenByPatient())
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "406 error has occurred | The appointment is taken by a patient!");
        appointmentRepository.deleteById(id);
    }

    /**
     * Story: Patients can view a doctor open appointment
     */
    @Transactional(readOnly = true)
    public List<AppointmentDTO> readAllOpenAppointments(LocalDate date) {
        List<AppointmentEntity> responseDTO;
        responseDTO = appointmentRepository.findAllByIsTakenByPatientIsFalseAndCreatedDateOrderByCreatedDateAsc(date).orElse(new ArrayList<>());
        if (ObjectUtils.isEmpty(responseDTO)) {
            return new ArrayList<>();
        }
        return responseDTO.stream().map(AppointmentDTO::toDTO).collect(Collectors.toList());
    }

    /**
     * Story: Patients can take an open appointment
     */
    @Transactional
    public void takeAnAppointment(PatientAppointmentRequestDTO requestDTO) throws BadRequestException {

        if (ObjectUtils.isEmpty(requestDTO.getFullName()) || ObjectUtils.isEmpty(requestDTO.getPhoneNumber()))
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Full name and phone number can not be empty !");

        AppointmentEntity appointmentEntity = appointmentRepository.findById(requestDTO.getAppointmentId()).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST.value(), "The given appointment is not present or id is invalid !"));
        if (appointmentEntity.getIsTakenByPatient())
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "The appointment was already taken by another patient!");

        PatientEntity patientEntity = patientRepository.findByPhoneNumber(requestDTO.getPhoneNumber()).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Patient not found"));
        synchronized (this) {
            int affectedRows = appointmentRepository.takeAppointment(appointmentEntity.getId(), patientEntity);
            if (affectedRows == 0)
                throw new RuntimeException("The taking appointment process was not successful.");
        }


    }

    /**
     * Story: Patients can view their own appointments
     */
    public List<AppointmentDTO> readAllAppointmentsForPatients(String phoneNumber) {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByPatientEntity_PhoneNumberAndIsTakenByPatientTrue(phoneNumber).orElse(new ArrayList<>());
        if (appointmentEntities.isEmpty())
            return new ArrayList<>();
        return appointmentEntities.stream().map(AppointmentDTO::toDTO).collect(Collectors.toList());
    }
}
