package com.offline.android.di.auth

import com.offline.android.ui.activity.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthModule::class,AuthFragmentBuildersModule::class,AuthViewModelModule::class])
    abstract fun contributeActivity() : AuthActivity



}