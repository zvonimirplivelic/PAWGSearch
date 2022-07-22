package com.zvonimirplivelic.pawgsearch

import android.app.Application
import timber.log.Timber


class PAWGSearchApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}