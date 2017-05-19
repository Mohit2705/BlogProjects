package com.example.mohsharma.autofillapidemo.repositery

import com.example.mohsharma.autofillapidemo.model.Repo
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RepoRepositery(private val repoInterface: RepoInterface) {

    fun fetchMovies(query: String, observer: Observer<List<Repo>>): Subscription {
        return repoInterface.fetchAllRepos(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}
