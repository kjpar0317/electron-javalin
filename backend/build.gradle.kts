import org.gradle.jvm.tasks.Jar

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
	implementation("io.javalin:javalin:5.4.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
	implementation("org.slf4j:slf4j-simple:2.0.6")
	implementation("com.zaxxer:HikariCP:5.0.1")
}


tasks.withType<Jar> {
	manifest {
		attributes["Main-Class"] = "MainApplicationKt"
	}
	from(configurations.compileClasspath.get().map { if (it.isDirectory()) it else zipTree(it) })
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
