package com.ynov.kotlin.rickmorty.presentation

import android.app.Application
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.DataRepository

class RMApplication : Application() {
    companion object {
        lateinit var app: RMApplication
    }

    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()
        app = this
        initInjection()
    }

    private fun initInjection() {
        dataRepository = DataRepository(ApiManager())
    }
}