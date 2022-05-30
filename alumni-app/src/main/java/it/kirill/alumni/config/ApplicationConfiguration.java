package it.kirill.alumni.config;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.repository.AlumniRepository;
import it.kirill.alumni.service.AlumniDtoHelper;
import it.kirill.alumni.service.AlumniDtoHelperImpl;
import it.kirill.alumni.service.AlumniService;
import it.kirill.alumni.service.AlumniServiceImpl;
import it.kirill.alumni.service.validation.ValidationFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AlumniService alumniService(AlumniRepository alumniRepository, AlumniDtoHelper alumniDtoHelper, ValidationFacade<AlumniDto> alumniDtoValidationFacade) {
        return new AlumniServiceImpl(alumniRepository, alumniDtoHelper, alumniDtoValidationFacade);
    }

    @Bean
    public AlumniDtoHelper alumniDtoHelper() {
        return new AlumniDtoHelperImpl();
    }
}
