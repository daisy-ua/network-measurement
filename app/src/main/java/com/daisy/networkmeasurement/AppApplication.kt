package com.daisy.networkmeasurement

import android.app.Application
import com.daisy.networkmeasurement.core.database.di.databaseModule
import com.daisy.networkmeasurement.core.di.configModule
import com.daisy.networkmeasurement.di.appModule
import com.daisy.networkmeasurement.feature.test.speedtest.di.speedTestModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(
                configModule,
                databaseModule,
                speedTestModule,
                appModule
            )
        }
    }
}