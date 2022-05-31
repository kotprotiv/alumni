package it.kirill.alumni.controller;

import it.kirill.alumni.TestAlumniSupplier;
import it.kirill.alumni.service.AlumniService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumniControllerTest {

    @MockBean
    private AlumniService alumniService;

    @LocalServerPort
    private int port;

    private String url;

    @Value("classpath:request.json")
    private Resource resource;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void init() {
        url = "http://localhost:" + port;

        doNothing().when(alumniService).save(eq(TestAlumniSupplier.supplyDto()));
        when(alumniService.find(eq(TestAlumniSupplier.NAME), eq("master"), any(PageRequest.class))).thenReturn(TestAlumniSupplier.supplyMap());
    }

    @Test
    void save() throws IOException {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/api/v1/alumni")
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), UTF_8));
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity response = restTemplate
                .withBasicAuth("user", "user")
                .postForEntity(uri, request, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void get() {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .path("/api/v1/alumni")
                .queryParam("name", TestAlumniSupplier.NAME)
                .queryParam("educationLevel", "master")
                .queryParam("page", 0)
                .queryParam("size", 1)
                .build()
                .toUri();

        ResponseEntity<Map> response = restTemplate
                .withBasicAuth("user", "user")
                .getForEntity(uri, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}