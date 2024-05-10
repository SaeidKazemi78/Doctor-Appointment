package com.corporation.doctorsappointment.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "PATIENTS_SEQ", sequenceName = "PATIENTS_SEQ", allocationSize = 1)
@Table(name = "tbl_patient")
public class PatientEntity {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENTS_SEQ")
    private Long id;

    @Column(name = "full_name", nullable = false)
    @NotNull(message = "Full name can not be empty !")
    private String fullName;

    @Column(name = "phone_number", nullable = false,unique = true)
    @NotNull(message = "Phone number can not be empty !")
    private String phoneNumber;


}
