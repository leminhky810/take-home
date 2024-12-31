plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
}

android {
    namespace = "com.minhky.core.domain"
}

dependencies {

    api(projects.core.common)
    api(projects.core.preferences)
    api(projects.core.data)

}