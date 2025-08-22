plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.7.0"
}

group = "codigo-rapido"
version = "0.0.1-SNAPSHOT"
description = "co-shop-core"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
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
	// todo: config db
	// implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// OpenAPI Generator dependencies
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.21")

	// Springdoc für Boot 3 (MVC)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	// JPA + Postgres
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")
}

val openApiSpec = "$projectDir/src/main/resources/openapi/api.yaml"
val basePkg = "codigorapido.coshopcore"

openApiGenerate {
	generatorName.set("spring")
	inputSpec.set(openApiSpec)
	outputDir.set("$buildDir/generated/openapi")
	apiPackage.set("$basePkg.api")
	modelPackage.set("$basePkg.model")
	invokerPackage.set("$basePkg.invoker")
	library.set("spring-boot")
	configOptions.set(
		mapOf(
			"useSpringBoot3" to "true",
			"useJakartaEe" to "true",
			"delegatePattern" to "true",
			"interfaceOnly" to "true",
			"dateLibrary" to "java8",
			"hideGenerationTimestamp" to "true",
			"configPackage" to "$basePkg.config",
			"useSpringDoc" to "false"
		)
	)
}

tasks.named("compileJava") { dependsOn(tasks.named("openApiGenerate")) }

sourceSets {
	main {
		java {
			srcDir("$buildDir/generated/openapi/src/main/java")
		}
	}
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

tasks.withType<Test> {
	useJUnitPlatform()
}
