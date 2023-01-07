plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-smallrye-opentracing")
    implementation("io.quarkus:quarkus-rest-client-jsonb")
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
    implementation("io.quarkus:quarkus-rest-client")
    implementation("io.quarkus:quarkus-resteasy-jsonb")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-logging-json")
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-micrometer")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-container-image-jib")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "de.qaware.cloudcomputing"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
