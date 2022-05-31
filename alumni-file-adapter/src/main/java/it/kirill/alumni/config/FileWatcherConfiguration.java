package it.kirill.alumni.config;

import it.kirill.alumni.messaging.RequestProducer;
import it.kirill.alumni.service.DirectoryWatcherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Path;

@Configuration
public class FileWatcherConfiguration {

    @Bean
    public DirectoryWatcherService directoryWatcherService(RequestProducer requestProducer) throws IOException {
        return new DirectoryWatcherService(Path.of("./in"), requestProducer);
    }

}