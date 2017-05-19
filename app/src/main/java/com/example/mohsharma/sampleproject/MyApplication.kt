package com.example.mohsharma.sampleproject

import android.app.Application
import com.example.mohsharma.sampleproject.dagger.DaggerRepositoryComponent
import com.example.mohsharma.sampleproject.dagger.RepositoryComponent
import com.example.mohsharma.sampleproject.dagger.RepositoryModule

class MyApplication : Application() {
    lateinit var repositoryComponent: RepositoryComponent
        private set

    override fun onCreate() {
        super.onCreate()

        repositoryComponent = DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryModule())
                .build()
    }
}
