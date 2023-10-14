plugins {
//	kotlin("jvm") version "1.8.0"
	kotlin("jvm") version "1.9.10"
}

group = "com.kjpar0317"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10")
	implementation("io.javalin:javalin:5.6.1")

	api("org.jetbrains.exposed:exposed:0.17.14")
	api("org.slf4j:slf4j-simple:2.0.9")
	api("com.zaxxer:HikariCP:5.0.1")
	api("com.fasterxml.jackson.core:jackson-databind:2.15.3")
	api("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
	api("com.auth0:java-jwt:4.4.0")
	api("io.github.microutils:kotlin-logging-jvm:3.0.5")
	api("com.sksamuel.hoplite:hoplite-core:2.7.5")
	api("com.sksamuel.hoplite:hoplite-yaml:2.7.5")

	testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = "11"
	}
}
