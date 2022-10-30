@file:Suppress("UNCHECKED_CAST")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream

plugins {
    war
    // Idea
    idea
    id("org.jetbrains.gradle.plugin.idea-ext")

    // Spring
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    // Kotlin
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")

    // Checkstyle
    id("org.jlleitschuh.gradle.ktlint")
    id("com.diffplug.spotless")
}

java.sourceCompatibility = JavaVersion.VERSION_13

repositories {
    mavenCentral()
}

val valtimoVersion: String by project

dependencies {

    implementation("com.ritense.valtimo:audit:$valtimoVersion")
    implementation("com.ritense.valtimo:besluit:$valtimoVersion")
    implementation("com.ritense.valtimo:connector:$valtimoVersion")
    implementation("com.ritense.valtimo:contactmoment:$valtimoVersion")
    implementation("com.ritense.valtimo:contract:$valtimoVersion")
    implementation("com.ritense.valtimo:core:$valtimoVersion")
    implementation("com.ritense.valtimo:document:$valtimoVersion")
    implementation("com.ritense.valtimo:documenten-api:$valtimoVersion")
    implementation("com.ritense.valtimo:form:$valtimoVersion")
    implementation("com.ritense.valtimo:form-link:$valtimoVersion")
    implementation("com.ritense.valtimo:haalcentraal-brp:$valtimoVersion")
    implementation("com.ritense.valtimo:keycloak-iam:$valtimoVersion")
    implementation("com.ritense.valtimo:klant:$valtimoVersion")
    implementation("com.ritense.valtimo:local-document-generation:$valtimoVersion")
    implementation("com.ritense.valtimo:milestones:$valtimoVersion")
    implementation("com.ritense.valtimo:objects-api:$valtimoVersion")
    implementation("com.ritense.valtimo:objecten-api:$valtimoVersion")
    implementation("com.ritense.valtimo:objecttypen-api:$valtimoVersion")
    implementation("com.ritense.valtimo:openzaak:$valtimoVersion")
    implementation("com.ritense.valtimo:plugin-valtimo:$valtimoVersion")
    implementation("com.ritense.valtimo:process-document:$valtimoVersion")
    implementation("com.ritense.valtimo:smartdocuments:$valtimoVersion")
    implementation("com.ritense.valtimo:view-configurator:$valtimoVersion")
    implementation("com.ritense.valtimo:web:$valtimoVersion")
    implementation("com.ritense.valtimo:wordpress-mail:$valtimoVersion")
    implementation("com.ritense.valtimo:zaken-api:$valtimoVersion")
    implementation("com.ritense.valtimo:objecten-api-authentication:$valtimoVersion")
    implementation("com.ritense.valtimo:test-utils-common:$valtimoVersion")

    implementation("org.postgresql:postgresql:42.3.1")

    // Kotlin logger
    implementation("io.github.microutils:kotlin-logging:2.1.21")

    // Testing
    testImplementation("com.ritense.valtimo:test-utils-common:$valtimoVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.camunda.bpm.assert:camunda-bpm-assert:15.0.0")
    testImplementation("org.camunda.bpm.extension:camunda-bpm-junit5:1.1.0")
    testImplementation("org.camunda.bpm.extension:camunda-bpm-assert:1.2")
    testImplementation("org.camunda.bpm.extension:camunda-bpm-assert-scenario:1.1.1")
    testImplementation("org.camunda.bpm.extension.mockito:camunda-bpm-mockito:5.16.0")
    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "13"
    }
}

apply(from = "gradle/environment.gradle.kts")
val configureEnvironment = extra["configureEnvironment"] as (task: ProcessForkOptions) -> Unit

tasks.bootRun {
    val t = this
    doFirst {
        configureEnvironment(t)
    }
}