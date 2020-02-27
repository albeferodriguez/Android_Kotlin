package com.example.crimenintent

import android.app.Application
import com.example.crimenintent.CrimeRepository

class CriminalIntentApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}