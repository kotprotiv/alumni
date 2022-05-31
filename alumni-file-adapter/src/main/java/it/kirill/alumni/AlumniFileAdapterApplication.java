package it.kirill.alumni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AlumniFileAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlumniFileAdapterApplication.class, args);
    }

}
