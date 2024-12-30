package com.elon.photoismcompose.core.preferences.di

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

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    private const val PREFS_NAME = "PreferencesName"

    @Named(PREFS_NAME)
    @Provides
    @Singleton
    fun providesSharedPreferencesName() = "app_secret_shared_prefs"

    @Provides
    @Singleton
    fun providesMasterKey(
        @ApplicationContext context: Context
    ) = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

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