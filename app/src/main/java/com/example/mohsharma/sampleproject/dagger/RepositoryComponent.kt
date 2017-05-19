package com.example.mohsharma.sampleproject.dagger

import com.example.mohsharma.sampleproject.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {
    fun inject(activity: MainActivity)
}

