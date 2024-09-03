import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.service)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "${libs.versions.applicationId.get()}.app"
    compileSdk = libs.versions.androidSDK.get().toInt()
    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.androidSDK.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    signingConfigs {
        create("release") {
            val keyStorePropertiesFile = file("keystore/keystore.properties")
            if (!keyStorePropertiesFile.exists()) {
                throw GradleException("keystore.properties file not found")
            }
            if (!keyStorePropertiesFile.canRead()) {
                throw GradleException("keystore.properties file is not readable")
            }

            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keyStorePropertiesFile))
            storeFile = file("keystore/${keystoreProperties["storeFile"]}")
            storePassword = keystoreProperties["storePassword"].toString()
            keyPassword = keystoreProperties["keyPassword"].toString()
            keyAlias = keystoreProperties["keyAlias"].toString()
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            // applicationIdSuffix = ".release"
            versionNameSuffix = "-release"
            isMinifyEnabled = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            versionNameSuffix = "-debug"
        }
    }
    applicationVariants.all {
        outputs.all {
            val versionName = versionName
            val versionCode = versionCode
            (this as BaseVariantOutputImpl).outputFileName = "PokeFight-$versionName-$versionCode.apk"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
    implementation(project.dependencies.platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.android)
}