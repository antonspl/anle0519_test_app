plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.7"
    id("org.springframework.boot") version "3.4.3"
    id("com.palantir.docker") version "0.35.0"
}

group = "org.anle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core")
}

tasks.test {
    useJUnitPlatform()
}

tasks.dockerPrepare {
    dependsOn(tasks.bootJar)
}

docker {
    name = "anle0519/testapp:".plus(version)
    files("/build/libs/test-project-1.0-SNAPSHOT.jar")
    buildArgs(mapOf("JAR_FILE" to "test-project-1.0-SNAPSHOT.jar"))
    setDockerfile(file("Dockerfile"))
}