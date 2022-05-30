package it.kirill.alumni.messaging;


import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.service.AlumniService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RequestListener {

    private final AlumniService alumniService;

    public RequestListener(AlumniService alumniService) {
        this.alumniService = alumniService;
    }


    @RabbitListener(queues = "#{QueueName.REQUEST.getName()}")
    public void listen(AlumniDto in) {
        alumniService.save(in);
    }
}
