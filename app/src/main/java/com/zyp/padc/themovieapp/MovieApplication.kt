package com.zyp.padc.themovieapp

import android.app.Application
import com.zyp.padc.themovieapp.data.models.MovieModelImpl

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}