package com.offline.android.di.auth

import android.content.SharedPreferences
import com.offline.android.api.auth.networkResponse.SignuoAuthService
import com.offline.android.session.SessionManager
import com.offline.android.ui.respositries.AuthRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): SignuoAuthService {
        return retrofitBuilder
            .build()
            .create(SignuoAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        signuoAuthService: SignuoAuthService, sessionManager: SessionManager
    ): AuthRepository {
        return AuthRepository(
            signuoAuthService,
            sessionManager
        )
    }

}