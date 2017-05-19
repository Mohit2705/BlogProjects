package com.example.mohsharma.sampleproject.dagger

import com.example.mohsharma.sampleproject.BuildConfig
import com.example.mohsharma.sampleproject.repositery.DataRepository
import com.example.mohsharma.sampleproject.service.RepoInterface
import com.example.mohsharma.sampleproject.service.RepoService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBaseURL(): HttpUrl {
        return HttpUrl.Builder()
                .scheme("https")
                .host("api.github.com")
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): Interceptor {
        val logLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(logLevel)

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetroFit(baseURL: HttpUrl, okHttpClient: OkHttpClient): Retrofit {
        val gson = Gson()
        return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun privateRepoInterface(retrofit: Retrofit): RepoInterface {
        return retrofit.create(RepoInterface::class.java)
    }

    @Provides
    @Singleton
    fun privateRepoService(api: RepoInterface): RepoService {
        return RepoService(api)
    }

    @Provides
    @Singleton
    fun privateDataRepositery(repoService: RepoService): DataRepository {
        return DataRepository(repoService)
    }


}

