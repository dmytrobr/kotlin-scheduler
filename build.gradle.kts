import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test

plugins {
    kotlin("jvm") version "1.2.50"
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile("org.assertj:assertj-core:3.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

tasks.withType<Test> {
    useJUnitPlatform{
        includeEngines("junit-jupiter")
    }
}
repositories {
    mavenCentral()
}
