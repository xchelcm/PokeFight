package com.idax.pokefight.android

import android.app.Application
import com.idax.pokefight.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class PokeFight: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PokeFight)
        }
    }
}