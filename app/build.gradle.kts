import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.minhky.project.AppBuildType
import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.project.android.application)
    alias(libs.plugins.project.android.application.compose)
    alias(libs.plugins.project.android.application.flavors)
    alias(libs.plugins.project.android.application.firebase)
    alias(libs.plugins.project.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.minhky.takehome"
    defaultConfig {
        applicationId = "com.minhky.takehome"
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("config") {
            storeFile = file("../keystore.jks")
            storePassword = gradleLocalProperties(rootDir, providers)["KEY_STORE_PASSWORD"] as String?
            keyAlias = gradleLocalProperties(rootDir, providers).getProperty("KEY_ALIAS")
            keyPassword = gradleLocalProperties(rootDir, providers).getProperty("KEY_ALIAS_PASSWORD")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = AppBuildType.DEBUG.applicationIdSuffix
            signingConfig = signingConfigs["config"]
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = AppBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs["config"]
            // Ensure Baseline Profile is fresh for release builds.
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.core)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.kotlinx.serialization.json)

    ksp(libs.hilt.compiler)

    kspTest(libs.hilt.compiler)

    testImplementation(libs.kotlin.test)

    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.kotlin.test)

    // Timber
    implementation(libs.timber)
}

dependencyGuard {
    configuration("prodReleaseRuntimeClasspath")
}