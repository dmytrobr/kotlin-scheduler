plugins {
    application
    kotlin("jvm") version "1.2.50"
}

application {
    mainClassName = "com.dmytrobr.Scheduler"
}

dependencies {
    compile(kotlin("stdlib"))
}

repositories {
    jcenter()
}
