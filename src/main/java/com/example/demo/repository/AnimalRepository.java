package com.example.demo.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

	@Query("SELECT * FROM animal WHERE updated_at > :lastUpdate")
	List<Animal> findByUpdatedAtGreaterThan(@Param("lastUpdate") OffsetDateTime lastUpdate);

}
