package it.kirill.alumni.messaging;


import it.kirill.alumni.model.QueueName;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.PostConstruct;

public class RequestProducer {

    private final RabbitTemplate template;

    private static final String TEST_JSON = "{\n" +
            "  \"name\": \"nome\",\n" +
            "  \"addresses\": [\n" +
            "    {\n" +
            "      \"street\": \"streetname\",\n" +
            "      \"number\": \"22\",\n" +
            "      \"country\": \"country\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"education\": {\n" +
            "    \"master\": {\n" +
            "      \"university\": \"PolitecnicoMilano\",\n" +
            "      \"year\": 2004\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public RequestProducer(RabbitTemplate template) {
        this.template = template;
    }

    @PostConstruct
    public void send() {
        template.convertAndSend(QueueName.REQUEST.getName(), TEST_JSON);
    }
}
