import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.7.9"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "com.search"
version = "1.0.0-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}
buildscript {
	repositories {
		mavenCentral()
	}
}

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "java")
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-kapt")
	apply(plugin = "kotlin-jpa")
	apply(plugin = "kotlin-spring")

	group = "com.search.blog"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_17

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	repositories {
		mavenCentral()
	}

	allOpen {
		annotation("javax.persistence.Entity")
		annotation("javax.persistence.Embeddable")
		annotation("javax.persistence.MappedSuperclass")
	}

	noArg {
		annotation("javax.persistence.Entity")
		annotation("javax.persistence.Embeddable")
		annotation("javax.persistence.MappedSuperclass")
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		runtimeOnly("com.h2database:h2")
		testImplementation("org.springframework.boot:spring-boot-starter-test")

	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

project(":application") {
	dependencies {
		api(project(":search"))
		api(project(":popular-searches"))
	}
	tasks.getByName<BootJar>("bootJar") {
		enabled = true
		launchScript()
	}
}

project(":search") {
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-webflux")
		implementation("io.github.resilience4j:resilience4j-spring-boot2:2.0.2")
		implementation("io.github.resilience4j:resilience4j-all:2.0.2")
		implementation("org.apache.httpcomponents:httpclient")
	}
	tasks.getByName<Jar>("jar") { enabled = true }
	tasks.getByName<BootJar>("bootJar") { enabled = false }
}

project(":popular-searches") {
	dependencies {
	}
	tasks.getByName<Jar>("jar") { enabled = true }
	tasks.getByName<BootJar>("bootJar") { enabled = false }
}