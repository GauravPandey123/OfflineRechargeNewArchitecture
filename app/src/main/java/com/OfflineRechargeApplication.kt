package com

import android.app.Activity
import android.app.Application
import com.offline.android.di.auth.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class OfflineRechargeApplication : Application() , HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

    override fun activityInjector() = dispatchingAndroidInjector
}