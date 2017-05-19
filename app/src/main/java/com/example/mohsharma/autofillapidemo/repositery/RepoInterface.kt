package com.example.mohsharma.autofillapidemo.repositery

import com.example.mohsharma.autofillapidemo.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface RepoInterface {

    @GET("users/{path}/repos")
    fun fetchAllRepos(
            @Path("path") path: String
    ): Observable<List<Repo>>
}