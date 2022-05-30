package it.kirill.alumni.config;

import it.kirill.alumni.messaging.RequestProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public RequestProducer requestListener(RabbitTemplate rabbitTemplate) {
        return new RequestProducer(rabbitTemplate);
    }
}
