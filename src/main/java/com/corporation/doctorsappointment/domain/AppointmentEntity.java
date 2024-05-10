package com.corporation.doctorsappointment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(name = "APPOINTMENT_SEQ", sequenceName = "APPOINTMENT_SEQ", allocationSize = 1)
@Table(name = "tbl_appointment")
public class AppointmentEntity {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPOINTMENT_SEQ")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "is_taken_by_patient", nullable = false)
    private Boolean isTakenByPatient = false;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Version
    @Column(name = "version")
    private Long version;


    /**
     * This is patient that is taken this appointment for instance
     */
    @JoinColumn(name = "pathient_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PatientEntity patientEntity;


}
