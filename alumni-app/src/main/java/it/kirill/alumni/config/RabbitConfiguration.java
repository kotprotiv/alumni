package it.kirill.alumni.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.kirill.alumni.messaging.RequestListener;
import it.kirill.alumni.model.QueueName;
import it.kirill.alumni.service.AlumniService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue requestQueue() {
        return new Queue(QueueName.REQUEST.getName(), true);
    }

    @Bean
    public RequestListener requestListener(AlumniService alumniService, ObjectMapper objectMapper) {
        return new RequestListener(alumniService, objectMapper);
    }
}
