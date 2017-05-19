package com.example.mohsharma.autofillapidemo.dagger

import com.example.mohsharma.autofillapidemo.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun inject(activity: MainActivity)
}

