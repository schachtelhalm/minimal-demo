package com.example.demo;

import org.testcontainers.containers.PostgreSQLContainer;

@SuppressWarnings("rawtypes")
abstract class AbstractContainerBaseTest {

	static final PostgreSQLContainer POSTGRESQL_CONTAINER;

	static {
		POSTGRESQL_CONTAINER = new PostgreSQLContainer("postgres:10").withDatabaseName("integration-tests-db")
				.withUsername("testuser").withPassword("mysecretpassword");
		POSTGRESQL_CONTAINER.start();
	}

}
