package com.offline.android.di.auth.state

sealed class AuthStateEvent {

    data class RegisterAttemptEvent(
        val email: String,
        val username: String,
        val password: String,
        val confirm_password: String,
        val lattitude: String,
        val logitude: String
    ) : AuthStateEvent()


    class None : AuthStateEvent()
}