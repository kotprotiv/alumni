package it.kirill.alumni.controller;

import it.kirill.alumni.TestAlumniSupplier;
import it.kirill.alumni.model.exception.ValidationException;
import it.kirill.alumni.service.AlumniService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumniControllerTest {

    @MockBean
    private AlumniService alumniService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void init() {
        doNothing().when(alumniService).save(eq(TestAlumniSupplier.supplyDto()));
        when(alumniService.findAllByName(eq(TestAlumniSupplier.NAME), any(PageRequest.class))).thenReturn(TestAlumniSupplier.supplyMap());
    }

    @Test
    void save() throws ValidationException, IOException {
        String jsonRequest = IOUtils.toString(this.getClass().getResourceAsStream("/request.json"));

        restTemplate.postForLocation("http://localhost:" + port + "/alumni", jsonRequest);
        verify(alumniService, times(1)).save(eq(TestAlumniSupplier.supplyDto()));
    }

    @Test
    void getByName() {
        Map result = this.restTemplate.getForObject("http://localhost:" + port + "/alumni", Map.class);

//        verify(alumniService, times(1)).findAllByName();

        assertEquals(TestAlumniSupplier.supplyMap(), result);
    }
}