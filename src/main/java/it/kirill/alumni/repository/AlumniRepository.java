package it.kirill.alumni.repository;


import it.kirill.alumni.model.entity.Alumni;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface AlumniRepository extends MongoRepository<Alumni, UUID> {

    List<Alumni> findAllByName(String name);

    List<Alumni> findAllByName(String name, PageRequest pageRequest);
//    List<Alumni> findAllByEducation()
}
