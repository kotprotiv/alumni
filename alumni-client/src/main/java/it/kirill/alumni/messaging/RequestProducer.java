package it.kirill.alumni.messaging;


import it.kirill.alumni.model.QueueName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.PostConstruct;

@Slf4j
public class RequestProducer {

    private final RabbitTemplate template;

    private static final String TEST_JSON = "{\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"addresses\": [\n" +
            "    {\n" +
            "      \"street\": \"via via\",\n" +
            "      \"number\": \"22\",\n" +
            "      \"country\": \"italia\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"education\": {\n" +
            "    \"master\": {\n" +
            "      \"university\": \"PolitecnicoMilano\",\n" +
            "      \"year\": 2021\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public RequestProducer(RabbitTemplate template) {
        this.template = template;
    }

    @PostConstruct
    public void send() {
        log.info("Sending message");
        template.convertAndSend(QueueName.REQUEST.getName(), TEST_JSON);
    }
}
