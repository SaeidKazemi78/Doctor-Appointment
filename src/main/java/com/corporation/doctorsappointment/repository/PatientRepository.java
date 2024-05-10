package com.corporation.doctorsappointment.repository;

import com.corporation.doctorsappointment.domain.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity>findByPhoneNumber(String phone);
}
