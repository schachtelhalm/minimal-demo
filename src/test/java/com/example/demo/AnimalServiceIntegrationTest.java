package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.demo.model.Animal;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = { AnimalServiceIntegrationTest.Initializer.class })
public class AnimalServiceIntegrationTest extends AbstractContainerBaseTest {

	@Autowired
	private AnimalService serviceUnderTest;

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues
					.of("spring.datasource.url=" + POSTGRESQL_CONTAINER.getJdbcUrl(),
							"spring.datasource.username=" + POSTGRESQL_CONTAINER.getUsername(),
							"spring.datasource.password=" + POSTGRESQL_CONTAINER.getPassword())
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}

	@Test
	void someTestMethod() {
		List<Animal> result = serviceUnderTest.findByUpdatedAtGreaterThan(OffsetDateTime.now().minusHours(1));
		assertFalse(result.isEmpty());
	}

}
