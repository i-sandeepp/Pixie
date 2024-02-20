package com.sandeep.pixie.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 * Red Apple Technologies Private Limited.
 */
@HiltAndroidApp
class PixieApp: Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}