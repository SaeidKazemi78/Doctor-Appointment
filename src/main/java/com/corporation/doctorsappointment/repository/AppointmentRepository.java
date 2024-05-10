package com.corporation.doctorsappointment.repository;

import com.corporation.doctorsappointment.domain.AppointmentEntity;
import com.corporation.doctorsappointment.domain.PatientEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<List<AppointmentEntity>> findByPatientEntity_PhoneNumberAndIsTakenByPatientTrue(String phoneNumber);

    Optional<List<AppointmentEntity>> findAllByIsTakenByPatientIsFalseAndCreatedDateOrderByCreatedDateAsc(LocalDate localDate);

    @Modifying
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("update  AppointmentEntity  appointment set appointment.isTakenByPatient=true ,appointment.patientEntity=:patient where appointment.id=:appointmentId")
    int takeAppointment(@Param("appointmentId") Long appointmentId, @Param("patient") PatientEntity patient);
}
