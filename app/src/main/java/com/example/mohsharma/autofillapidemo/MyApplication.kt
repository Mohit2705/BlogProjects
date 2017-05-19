package com.example.mohsharma.autofillapidemo

import android.app.Application

import com.example.mohsharma.autofillapidemo.dagger.DaggerNetworkComponent
import com.example.mohsharma.autofillapidemo.dagger.NetworkComponent
import com.example.mohsharma.autofillapidemo.dagger.NetworkModule

class MyApplication : Application() {
    lateinit var networkComponent: NetworkComponent
        private set

    override fun onCreate() {
        super.onCreate()

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule())
                .build()
    }
}
