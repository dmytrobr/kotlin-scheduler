
plugins {
    kotlin("jvm") version "1.2.50"
    id("java")
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile("org.assertj:assertj-core:3.10.0")

    testCompile("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.2.0")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit"))
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        languageVersion = "1.2"
        apiVersion = "1.2"
        jvmTarget = "1.8"
        javaParameters = true   // Useful for reflection.
    }
}
//withParallelTests()
repositories {
    mavenCentral()
}
