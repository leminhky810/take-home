package com.minhky.core.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.minhky.core.model.AppSession
import kotlinx.coroutines.flow.flow

/**
 * A class that manages application shared preferences with encryption.
 *
 * @property prefs The encrypted shared preferences instance.
 */
class AppSharedPreferences(
    context: Context,
    storageName: String,
    masterKey: MasterKey,
) {

    private var prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        storageName,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * The access token stored in shared preferences.
     */
    var accessToken: String?
        get() = prefs[PREFS_ACCESS_TOKEN]
        set(value) = prefs.set(PREFS_ACCESS_TOKEN, value)

    /**
     * The refresh token stored in shared preferences.
     */
    var refreshToken: String?
        get() = prefs[PREFS_REFRESH_TOKEN]
        set(value) {
            prefs[PREFS_REFRESH_TOKEN] = value
        }

    /**
     * The FCM token stored in shared preferences.
     */
    var fcmToken: String?
        get() = prefs[PREFS_DEVICE_TOKEN]
        set(value) {
            prefs[PREFS_DEVICE_TOKEN] = value
        }

    /**
     * The user session state stored in shared preferences.
     */
    var userSession: String?
        get() = prefs[PREFS_USER_SESSION] ?: AppSession.FirstOpen.name
        set(value) {
            prefs[PREFS_USER_SESSION] = value
        }

    /**
     * Retrieves the user session state as a flow.
     *
     * @return A flow emitting the current user session state.
     */
    fun getUserSession() = flow<AppSession> {
        emit(AppSession.valueOf(userSession ?: AppSession.FirstOpen.name))
    }

    /**
     * The user ID stored in shared preferences.
     */
    var userId: Int?
        get() = prefs[PREFS_USER_ID]
        set(value) {
            prefs[PREFS_USER_ID] = value
        }

    /**
     * Authenticates the user by storing the access token, refresh token, and user ID.
     *
     * @param accessToken The access token to store.
     * @param refreshToken The refresh token to store.
     * @param userId The user ID to store.
     */
    fun authenticated(accessToken: String?, refreshToken: String?, userId: Int?) {
        if (accessToken == null) unAuthenticated()
        this@AppSharedPreferences.accessToken = accessToken
        this@AppSharedPreferences.refreshToken = refreshToken
        this@AppSharedPreferences.userSession = AppSession.Authenticated.name
        this@AppSharedPreferences.userId = userId
    }

    /**
     * Unauthenticates the user by clearing the access token, refresh token, FCM token, and user ID.
     */
    fun unAuthenticated() {
        this@AppSharedPreferences.accessToken = null
        this@AppSharedPreferences.refreshToken = null
        this@AppSharedPreferences.userSession = AppSession.UnAuthenticated.name
        this@AppSharedPreferences.fcmToken = null
        this@AppSharedPreferences.userId = null
    }

    companion object {
        const val PREFS_ACCESS_TOKEN = "accessToken"
        const val PREFS_REFRESH_TOKEN = "refreshToken"
        const val PREFS_DEVICE_TOKEN = "deviceToken"
        const val PREFS_USER_SESSION = "userSession"
        const val PREFS_USER_ID = "userId"
    }
}