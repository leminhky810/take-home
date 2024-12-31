package com.minhky.core.preferences.di

import android.content.Context
import androidx.security.crypto.MasterKey
import com.minhky.core.preferences.AppSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Dagger module for providing shared preferences dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    private const val PREFS_NAME = "PreferencesName"

    /**
     * Provides the name of the shared preferences file.
     *
     * @return The name of the shared preferences file.
     */
    @Named(PREFS_NAME)
    @Provides
    @Singleton
    fun providesSharedPreferencesName() = "app_secret_shared_prefs"

    /**
     * Provides the MasterKey for encrypted shared preferences.
     *
     * @param context The application context.
     * @return The MasterKey instance.
     */
    @Provides
    @Singleton
    fun providesMasterKey(
        @ApplicationContext context: Context
    ) = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    /**
     * Provides the AppSharedPreferences instance.
     *
     * @param context The application context.
     * @param storageName The name of the shared preferences file.
     * @param masterKey The MasterKey for encryption.
     * @return The AppSharedPreferences instance.
     */
    @Provides
    @Singleton
    fun providesSharedPreferences(
        @ApplicationContext context: Context,
        @Named(PREFS_NAME) storageName: String,
        masterKey: MasterKey,
    ) = AppSharedPreferences(
        context = context,
        storageName = storageName,
        masterKey = masterKey
    )
}