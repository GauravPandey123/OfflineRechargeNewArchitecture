package com.offline.android.di.auth

import android.app.Application
import com.OfflineRechargeApplication
import com.offline.android.di.AppModule
import com.offline.android.di.ViewModelFactoryModule
import com.offline.android.session.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {
    val sessionManager: SessionManager // must add here b/c injecting into abstract class

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent

    }
    fun inject(app: OfflineRechargeApplication)

}