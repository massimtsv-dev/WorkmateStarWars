package com.tsvigun.workmatestarwars

import android.app.Application
import com.tsvigun.workmatestarwars.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StarWarsApp)
            modules(appModule)
        }
    }

}