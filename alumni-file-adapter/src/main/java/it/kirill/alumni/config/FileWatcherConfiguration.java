package it.kirill.alumni.config;

import it.kirill.alumni.messaging.RequestProducer;
import it.kirill.alumni.service.DirectoryWatcherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Path;

@Configuration
public class FileWatcherConfiguration {

    @Bean
    public DirectoryWatcherService directoryWatcherService(RequestProducer requestProducer,
                                                           @Value("${incoming.address}") String incomingAddress) throws IOException {
        return new DirectoryWatcherService(Path.of(incomingAddress), requestProducer);
    }

}