package com.example.memorygame.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MemoryApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}