package it.kirill.alumni.messaging;


import it.kirill.alumni.model.QueueName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public class RequestProducerImpl implements RequestProducer {

    private final RabbitTemplate template;

    public RequestProducerImpl(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void send(String json) {
        log.info("Sending message: {}", json);
        template.convertAndSend(QueueName.REQUEST.getName(), json);
    }
}
