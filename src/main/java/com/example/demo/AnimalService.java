package com.example.demo;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalService {

	@NonNull
	private AnimalRepository animalRepository;

	public List<Animal> findByUpdatedAtGreaterThan(OffsetDateTime lastUpdate) {
		return animalRepository.findByUpdatedAtGreaterThan(lastUpdate);
	}

}
