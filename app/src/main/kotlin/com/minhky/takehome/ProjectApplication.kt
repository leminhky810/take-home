package com.minhky.takehome

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * Custom Application class for the project.
 *
 * This class sets up the logging and provides an ImageLoaderFactory for Coil.
 */
@HiltAndroidApp
class ProjectApplication : Application(), ImageLoaderFactory {

    @Inject
    lateinit var imageLoader: dagger.Lazy<ImageLoader>

    /**
     * Called when the application is starting, before any other application objects have been created.
     */
    override fun onCreate() {
        super.onCreate()
        setupLogging()
    }

    /**
     * Creates a new ImageLoader instance.
     *
     * @return The ImageLoader instance.
     */
    override fun newImageLoader(): ImageLoader = imageLoader.get()

    /**
     * Sets up logging for the application. If the build is in debug mode, a debug tree is planted for Timber.
     */
    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}