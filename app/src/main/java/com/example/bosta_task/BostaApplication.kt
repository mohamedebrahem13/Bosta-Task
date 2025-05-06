package com.example.bosta_task

import android.app.Application
import com.example.bosta_task.di.AppComponent
import com.example.bosta_task.di.DaggerAppComponent


class BostaApplication : Application() {

    lateinit var appComponent: AppComponent
        private set
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
