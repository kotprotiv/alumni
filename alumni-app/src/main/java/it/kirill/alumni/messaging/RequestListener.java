package it.kirill.alumni.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.service.AlumniService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class RequestListener {

    private final AlumniService alumniService;
    private final ObjectMapper objectMapper;

    public RequestListener(AlumniService alumniService, ObjectMapper objectMapper) {
        this.alumniService = alumniService;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = "requestQueue")
    public void listen(String in) throws JsonProcessingException {
        log.info("Got message: {}", in);
        AlumniDto alumniDto = objectMapper.readValue(in, AlumniDto.class);
        alumniService.save(alumniDto);
    }
}
