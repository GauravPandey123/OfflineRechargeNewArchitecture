package com.offline.android.di.auth

import com.offline.android.ui.fragments.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeRegisterFragment(): SignupFragment


}