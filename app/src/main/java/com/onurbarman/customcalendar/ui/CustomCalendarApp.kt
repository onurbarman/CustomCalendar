package com.onurbarman.customcalendar.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CustomCalendarApp @Inject constructor(): Application() {

    override fun onCreate() {
        super.onCreate()
    }

}