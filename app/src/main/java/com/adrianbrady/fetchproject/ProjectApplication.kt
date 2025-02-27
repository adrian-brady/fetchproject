package com.adrianbrady.fetchproject

import android.app.Application
import com.adrianbrady.fetchproject.data.AppContainer
import com.adrianbrady.fetchproject.data.DefaultAppContainer

class ProjectApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}