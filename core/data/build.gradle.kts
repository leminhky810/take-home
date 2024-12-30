plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.room)
    alias(libs.plugins.project.hilt)
    alias(libs.plugins.kotlin.serialization)
    id("kotlinx-serialization")
}

android {
    namespace = "com.minhky.core.data"

}

dependencies {

    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.preferences)
    api(projects.core.network)

//    testImplementation(libs.kotlinx.coroutines.test)
//    testImplementation(libs.kotlinx.serialization.json)
}