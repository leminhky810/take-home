package com.minhky.core.model

/**
 * Enum class representing the different states of the application session.
 */
enum class AppSession {
    /**
     * State when the application is opened for the first time.
     */
    FirstOpen,

    /**
     * State when the user is authenticated.
     */
    Authenticated,

    /**
     * State when the user is not authenticated.
     */
    UnAuthenticated
}