package com.minhky.core.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.minhky.core.model.AppSession
import kotlinx.coroutines.flow.flow

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

    var accessToken: String?
        get() = prefs[PREFS_ACCESS_TOKEN] ?: "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"
        set(value) = prefs.set(PREFS_ACCESS_TOKEN, value)

    var refreshToken: String?
        get() = prefs[PREFS_REFRESH_TOKEN]
        set(value) {
            prefs[PREFS_REFRESH_TOKEN] = value
        }

    var fcmToken: String?
        get() = prefs[PREFS_DEVICE_TOKEN]
        set(value) {
            prefs[PREFS_DEVICE_TOKEN] = value
        }

    var userSession: String?
        get() = prefs[PREFS_USER_SESSION] ?: AppSession.FirstOpen.name
        set(value) {
            prefs[PREFS_USER_SESSION] = value
        }

    fun getUserSession() = flow<AppSession> {
        emit(AppSession.valueOf(userSession ?: AppSession.FirstOpen.name))
    }

    var userId: Int?
        get() = prefs[PREFS_USER_ID]
        set(value) {
            prefs[PREFS_USER_ID] = value
        }

    fun authenticated(accessToken: String?, refreshToken: String?, userId: Int?) {
        if (accessToken == null) unAuthenticated()
        this@AppSharedPreferences.accessToken = accessToken
        this@AppSharedPreferences.refreshToken = refreshToken
        this@AppSharedPreferences.userSession = AppSession.Authenticated.name
        this@AppSharedPreferences.userId = userId
    }

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