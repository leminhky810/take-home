package com.minhky.core.preferences

import android.content.SharedPreferences

fun SharedPreferences.remove(key: String) {
    this.execute { it.remove(key) }
}

fun SharedPreferences.clearAll() {
    this.execute { it.clear() }
}

fun SharedPreferences.execute(operation: (SharedPreferences.Editor) -> Unit) {
    with(edit()) {
        operation(this)
        apply()
    }
}

inline operator fun <reified T : Any?> SharedPreferences.set(key: String, value: T?) {
    if (value == null) {
        remove(key)
        return
    }
    when (value) {
        is String? -> execute { it.putString(key, value) }
        is Int -> execute { it.putInt(key, value) }
        is Boolean -> execute { it.putBoolean(key, value) }
        is Float -> execute { it.putFloat(key, value) }
        is Long -> execute { it.putLong(key, value) }
        is Double -> execute { it.putString(key, value.toString()) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(key: String): T? =
    if (contains(key)) {
        when (T::class) {
            Boolean::class -> getBoolean(key, false) as T?
            String::class -> { getString(key, null) as T? }
            Int::class -> getInt(key, 0) as T?
            Float::class -> getFloat(key, 0f) as T?
            Long::class -> getLong(key, 0L) as T?
            Double::class -> (getString(key, "-1.0")?.toDoubleOrNull() ?: -1.0) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    } else null


