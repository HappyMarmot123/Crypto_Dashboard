plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
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
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("com.squareup.okhttp:okhttp:2.7.5")
	implementation("org.springframework.data:spring-data-commons:3.3.3")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.3.0")
	implementation("org.springframework.data:spring-data-mongodb:4.3.0")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")	
	implementation("org.json:json:20231013")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")	
}

tasks.withType<Test> {
	useJUnitPlatform()
}
