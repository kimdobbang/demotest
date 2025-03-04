plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter (기본 의존성)
    implementation("org.springframework.boot:spring-boot-starter")

    // Spring Boot Starter Web (REST API 개발을 위한 기본 의존성)
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Jackson (JSON 변환을 위한 기본 의존성, Spring Boot에 기본 포함되어 있지만 명시적으로 추가)
    implementation("com.fasterxml.jackson.core:jackson-databind")

    // Spring Boot Starter Test (테스트를 위한 의존성)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
