import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.regex.Pattern.compile

plugins {
	kotlin("jvm") version "1.8.0"
}

group = "com.kjpar0317"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
	implementation("io.javalin:javalin:5.4.2")

	api("org.slf4j:slf4j-simple:2.0.6")
	api("com.zaxxer:HikariCP:5.0.1")
	api("com.fasterxml.jackson.core:jackson-databind:2.14.2")
	api("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
	api("com.auth0:java-jwt:4.3.0")
	api("io.github.microutils:kotlin-logging-jvm:3.0.5")

	testImplementation("org.slf4j:slf4j-simple:2.0.6")
}
