import uk.gov.pipelines.config.ApkConfig

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.paparazzi)
    id("uk.gov.pipelines.android-lib-config")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        val apkConfig: ApkConfig by project.rootProject.extra
        namespace = "${apkConfig.applicationId}.componentsv2"
        compileSdk = apkConfig.sdkVersions.compile
        minSdk = apkConfig.sdkVersions.minimum
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.all {
            it.testLogging {
                events = setOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                )
            }
        }
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    androidTestImplementation(composeBom)
    implementation(composeBom)

    implementation(libs.androidx.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.core.ktx)
    implementation(project(":theme"))

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testmanifest)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.compose.ui.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestUtil(libs.androidx.test.orchestrator)

    testImplementation(libs.arch.core)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
}

mavenPublishingConfig {
    mavenConfigBlock {
        name.set(
            "Mobile Android Component Library Version 2",
        )
        description.set(
            """
            Use pre-built reusable components to build consistent apps - this will replace the
            components module with a more standardised approach.
            """.trimIndent(),
        )
    }
}
