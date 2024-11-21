import org.slf4j.Logger
import org.slf4j.LoggerFactory

plugins {
    val kotlinVersion = "1.9.24"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("idea")
    id("io.qameta.allure") version "2.12.0"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.seleniumhq.selenium:selenium-java:4.26.0")
    implementation("io.qameta.allure:allure-testng:2.29.0")
    implementation("org.slf4j:slf4j-api:2.0.0")
    implementation("ch.qos.logback:logback-classic:1.4.7")
    testImplementation("org.testng:testng:7.10.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.9.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.0")
}

val log: Logger = LoggerFactory.getLogger("Logger")

tasks.test {
    useTestNG {
        suites("src/test/resources/testrunners/zando.xml")
    }
    outputs.dir(file("build/allure-results"))

    reports {
        junitXml.required.set(false)
        html.required.set(false)
    }

    val browserValue = System.getenv("BROWSER") ?: project.findProperty("browser") ?: "chrome"
    val baseUrlValue = System.getenv("BASE_URL") ?: project.findProperty("base.url") ?: "https://en.zalando.de"

    systemProperty("browser", browserValue)
    systemProperty("baseUrl", baseUrlValue)
}

tasks.allureReport {
    doLast {
        val reportDir = file("build/reports/allure-report/allureReport/index.html")

        val serverUrl = System.getenv("IDE_SERVER_URL") ?: project.findProperty("ide.server.url")

        if (reportDir.exists()) {
            val localUrl =
                "$serverUrl${reportDir.absolutePath.substringAfter(File("build").absolutePath).replace(File.separatorChar, '/')}"
            log.info("Allure Report located on: $localUrl")
        } else {
            log.error("Allure report not found at ${reportDir.absolutePath}")
        }
    }
}

tasks.named("test").configure {
    finalizedBy(tasks.named("allureReport"))
}

kotlin {
    jvmToolchain(17)
}
