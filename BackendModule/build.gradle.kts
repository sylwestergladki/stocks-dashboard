
plugins {
	java
	war
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "pl.sylwestergladki"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("com.h2database:h2")
	implementation("org.springframework.kafka:spring-kafka")
	implementation ("org.hibernate:hibernate-core:6.5.0.Final")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.0")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.0")
	testImplementation ("org.mockito:mockito-core:4.11.0")
	testImplementation ("org.assertj:assertj-core:3.24.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
