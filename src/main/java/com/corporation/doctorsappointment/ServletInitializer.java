package com.corporation.doctorsappointment;

import com.corporation.doctorsappointment.domain.PatientEntity;
import com.corporation.doctorsappointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.Transactional;

public class ServletInitializer extends SpringBootServletInitializer  {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


}
