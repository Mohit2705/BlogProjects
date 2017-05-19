package com.example.mohsharma.sampleproject.service

import com.example.mohsharma.sampleproject.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface RepoInterface {

    @retrofit2.http.GET("users/{path}/repos")
    fun fetchAllRepos(
            @retrofit2.http.Path("path") path: String
    ): rx.Observable<List<Repo>>
}