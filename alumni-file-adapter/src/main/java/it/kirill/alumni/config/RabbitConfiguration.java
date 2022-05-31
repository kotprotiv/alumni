package it.kirill.alumni.config;

import it.kirill.alumni.messaging.RequestProducer;
import it.kirill.alumni.messaging.RequestProducerImpl;
import it.kirill.alumni.model.QueueName;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    public RequestProducer requestListener(RabbitTemplate rabbitTemplate) {
        return new RequestProducerImpl(rabbitTemplate);
    }
}
