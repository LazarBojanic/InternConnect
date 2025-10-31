val exposed_version: String by project
val koin_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgres_version: String by project
val java_jwt_version: String by project
val commons_email_version: String by project
val jbcrypt_version: String by project
val ktor_plugin_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("io.ktor.plugin") version "3.3.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.0-Beta2"
}

group = "com.internconnect"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

}

dependencies {
    implementation("io.ktor:ktor-server-cors")
    implementation("io.ktor:ktor-server-default-headers")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-openapi")
    implementation("io.ktor:ktor-server-swagger")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-client-core")
	implementation("io.ktor:ktor-server-netty")
	implementation("io.ktor:ktor-client-apache")
	implementation("io.ktor:ktor-server-csrf")
	implementation("io.ktor:ktor-server-resources")
	implementation("io.ktor:ktor-server-content-negotiation")
	implementation("io.ktor:ktor-server-html-builder")
	implementation("io.ktor:ktor-server-config-yaml")
	implementation("io.ktor:ktor-serialization-kotlinx-json")
	implementation("io.ktor:ktor-server-forwarded-header")
	implementation("io.ktor:ktor-server-call-logging")
	implementation("io.ktor:ktor-server-call-id")

	implementation("io.insert-koin:koin-ktor:$koin_version")
	implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
	implementation("org.postgresql:postgresql:$postgres_version")
	implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-json:$exposed_version")
	implementation("com.auth0:java-jwt:$java_jwt_version")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	implementation("org.apache.commons:commons-email:$commons_email_version")
	implementation("org.mindrot:jbcrypt:$jbcrypt_version")

}
