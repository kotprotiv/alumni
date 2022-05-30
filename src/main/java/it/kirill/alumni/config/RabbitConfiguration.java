package it.kirill.alumni.config;

import it.kirill.alumni.messaging.RequestListener;
import it.kirill.alumni.model.QueueName;
import it.kirill.alumni.service.AlumniService;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue requestQueue() {
        return new Queue(QueueName.REQUEST.getName(), true);
    }

    @Bean
    public RequestListener requestListener(AlumniService alumniService) {
        return new RequestListener(alumniService);
    }
}
