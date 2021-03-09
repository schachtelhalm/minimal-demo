package com.example.demo;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Animal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnimalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnimalController.class);

	@NonNull
	private AnimalService animalService;

	@RequestMapping(value = "/animals", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getAnimals(
			@RequestParam(value = "lastUpdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime lastUpdate) {

		LOGGER.debug("getAnimals() lastUpdate: '{}'", lastUpdate);
		try {
		List<Animal> animals = animalService.findByUpdatedAtGreaterThan(lastUpdate);
		LOGGER.debug("getAnimals() Found {} animals", animals.size());
		return ResponseEntity.ok(animals);
		} catch(Exception e) {
			LOGGER.error("getAnimals() Failed to get animals", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
