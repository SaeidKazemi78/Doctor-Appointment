package com.corporation.doctorsappointment;

import com.corporation.doctorsappointment.domain.PatientEntity;
import com.corporation.doctorsappointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Autowired
	private PatientRepository patientRepository;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		PatientEntity patient = new PatientEntity();
		patient.setFullName("Jon");
		patient.setPhoneNumber("09914294130");
		patientRepository.saveAndFlush(patient);

		PatientEntity patient2 = new PatientEntity();
		patient2.setFullName("Mohammad");
		patient2.setPhoneNumber("09124349191");
		patientRepository.saveAndFlush(patient2);
	}
}
