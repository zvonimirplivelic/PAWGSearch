package com.zvonimirplivelic.rawgsearch

import android.app.Application
import timber.log.Timber


class RAWGSearchApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}