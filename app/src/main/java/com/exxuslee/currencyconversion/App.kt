package com.exxuslee.currencyconversion

import android.app.Application
import com.exxuslee.currencyconversion.di.presentationModule
import com.exxuslee.data.di.databaseModule
import com.exxuslee.data.di.networkingModule
import com.exxuslee.data.di.repositoryModule
import com.exxuslee.domain.di.interactionModule
import com.google.firebase.FirebaseApp
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Mayokun Adeniyi on 23/07/2020.
 */

class App : Application() {
    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(appModules + domainModules + dataModules)
        }
    }
}

val appModules = listOf(presentationModule)
val domainModules = listOf(interactionModule)
val dataModules = listOf(networkingModule, repositoryModule, databaseModule)