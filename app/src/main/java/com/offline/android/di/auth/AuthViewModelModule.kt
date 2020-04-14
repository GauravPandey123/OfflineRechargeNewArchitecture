package com.offline.android.di.auth

import androidx.lifecycle.ViewModel
import com.offline.android.di.ViewModelKey
import com.offline.android.ui.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel



}