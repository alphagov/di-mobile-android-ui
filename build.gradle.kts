buildscript {
    val dep_jacoco by rootProject.extra { "0.8.8" }
    val minAndroidVersion by rootProject.extra { 29 }
    val compileAndroidVersion by rootProject.extra { 33 }
    val androidBuildToolsVersion by rootProject.extra { "33.0.0" }
    val composeKotlinCompilerVersion by rootProject.extra { "1.5.0" }
    val configDir by rootProject.extra { "$rootDir/config" }

    val localProperties = java.util.Properties()
    if (rootProject.file("local.properties").exists()) {
        println(localProperties)
        localProperties.load(java.io.FileInputStream(rootProject.file("local.properties")))
    }

    fun findPackageVersion(): String {
        var version = "1.0.0"

        println(localProperties)
        if (rootProject.hasProperty("packageVersion")) {
            version = rootProject.property("packageVersion") as String
        } else if (localProperties.getProperty("packageVersion") != null) {
            version = localProperties.getProperty("packageVersion") as String
        }

        println("packageVersion is set to $version")
        return version
    }

    val packageVersion by rootProject.extra { findPackageVersion() }
}

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply false
    id("app.cash.paparazzi") apply false
}

apply {
    from("$rootDir/config/styles/tasks.gradle")
}

tasks.register("check") {
    dependsOn("vale")
}
