
plugins {
	id("org.springframework.boot") version "2.3.9.RELEASE"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("java")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	jcenter()
}

dependencies {

	"implementation"("org.springframework.boot:spring-boot-starter-web")
	"implementation"("org.springframework.boot:spring-boot-starter-data-jdbc")
	"implementation"("org.postgresql:postgresql")
	"implementation"("org.flywaydb:flyway-core")

	"compileOnly"("org.projectlombok:lombok")
    "testCompileOnly"("org.projectlombok:lombok")
    "annotationProcessor"("org.projectlombok:lombok")
    "testAnnotationProcessor"("org.projectlombok:lombok")

    "testCompile"("org.junit.jupiter:junit-jupiter-api")
    "testCompile"("org.junit.jupiter:junit-jupiter-params")
	"testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine")

    "testCompile"("org.testcontainers:testcontainers:1.15.2")	
	"testCompile"("org.testcontainers:junit-jupiter:1.15.2")
	"testCompile"("org.testcontainers:postgresql:1.15.2")

	"testImplementation"("org.springframework.boot:spring-boot-starter-test") {
		exclude(module="junit")
		exclude(group="org.junit.vintage", module="junit-vintage-engine")
	}

}


tasks.withType<Test>().all {
	useJUnitPlatform()
	systemProperty("spring.profiles.active", "test")
}

tasks.withType<JavaCompile>().all {
	sourceCompatibility = "11"
	targetCompatibility = "11"
}
