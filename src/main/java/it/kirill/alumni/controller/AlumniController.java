package it.kirill.alumni.controller;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.exception.NoDataFoundException;
import it.kirill.alumni.model.exception.ValidationException;
import it.kirill.alumni.service.AlumniService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class AlumniController {

    private final AlumniService alumniService;

    @PostMapping("alumni")
    public void save(@RequestBody AlumniDto alumniDtos) {
        alumniService.save(alumniDtos);
    }

    @GetMapping(value = "alumni", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getByName(@RequestParam("name") String name,
                                         @RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return alumniService.findAllByName(name, pageRequest);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<String> noDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> noDataFoundException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}


