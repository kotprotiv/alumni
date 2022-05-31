package it.kirill.alumni.config;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.service.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class ValidationConfiguration {

    @Bean
    public ValidationFacade<AlumniDto> alumniDtoValidationFacade(List<ValidationService<AlumniDto>> validationServices) {
        return new CommonValidationFacade<>(validationServices);
    }

    @Bean
    @Order(1)
    public ValidationService<AlumniDto> alumniAddressFullnessValidationService() {
        return new AlumniAddressFullnessValidationService();
    }

    @Bean
    @Order(2)
    public ValidationService<AlumniDto> alumniAddressStreetNameValidationService() {
        return new AlumniAddressStreetNameValidationService();
    }

    @Bean
    @Order(3)
    public ValidationService<AlumniDto> alumniAddressStreetNumberValidationService() {
        return new AlumniAddressStreetNumberValidationService();
    }
}
