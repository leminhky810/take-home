/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
    alias(libs.plugins.kotlin.serialization)
    id(libs.plugins.secrets.get().pluginId)
    id("kotlinx-serialization")
//    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.minhky.core.network"

    buildFeatures {
        buildConfig = true
    }

}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)
    api(projects.core.preferences)
    api(libs.kotlinx.serialization.json)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.okhttp.logging)
    api(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}