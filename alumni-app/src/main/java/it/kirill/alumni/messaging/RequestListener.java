package it.kirill.alumni.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.service.AlumniService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;

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
        List<AlumniDto> alumniDto = objectMapper.readValue(in, new TypeReference<List<AlumniDto>>() {
        });
        alumniService.saveAll(alumniDto);
    }
}
