import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.googleGmsGoogleServices)
//    alias(libs.plugins.googleGmsGoogleServices)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    android {
       namespace = "org.carecampuspartner.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
       withDeviceTestBuilder {
           sourceSetTreeName = "test"
       }.configure {
           instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation("io.insert-koin:koin-android:4.1.1")

            implementation(libs.ktor.client.okhttp)
            implementation(libs.compose.uiToolingPreview)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation("io.insert-koin:koin-android:4.1.1")

            implementation("androidx.datastore:datastore-preferences:1.1.7")

            implementation(libs.ktor.client.okhttp)

        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            // icons
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            // navigator
            val voyagerVersion = "1.1.0-beta03"
            implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
            // Voyager bottom navigation (if needed)
            implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
            implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
            // Voyager transitions (optional)
            implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

            // Image loading - Use Coil 3.x for better KMP support
//            implementation("io.coil-kt.coil3:coil-compose:3.0.0")
            implementation("io.coil-kt.coil3:coil-network-ktor3:3.3.0")

            implementation("io.coil-kt.coil3:coil-compose:3.3.0")
//            implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")



            // DI - Koin for Compose Multiplatform
            implementation("io.insert-koin:koin-compose:4.1.1")
            implementation("io.insert-koin:koin-core:4.1.1")
            implementation("io.insert-koin:koin-compose-viewmodel:4.1.1")

            // Serialization
//            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
            // Serialization
//            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
//            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.serialization.json)


            implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
            // Voyager bottom navigation (if needed)
            implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
            implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
            // Voyager transitions (optional)
            implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")


//            implementation(libs.ktor.client.core)

            implementation("io.ktor:ktor-client-content-negotiation:3.3.0")

            implementation("io.ktor:ktor-client-logging:3.3.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.0")
//            implementation("io.ktor:ktor-client-cio:3.3.0")

            implementation("androidx.datastore:datastore:1.1.7")
            // The Preferences DataStore library
            implementation("androidx.datastore:datastore-preferences:1.1.7")

//            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.2")

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
            implementation("cafe.adriel.voyager:voyager-navigator:1.1.0-beta03")
            implementation("cafe.adriel.voyager:voyager-transitions:1.1.0-beta03")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

dependencies {
//    implementation(libs.firebase.auth)
//    implementation(libs.androidx.credentials)
//    implementation(libs.androidx.credentials.play.services.auth)
//    implementation(libs.googleid)
    androidRuntimeClasspath(libs.compose.uiTooling)
}