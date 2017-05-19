package com.example.mohsharma.sampleproject.service

import com.example.mohsharma.sampleproject.model.Repo
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RepoService(private val repoInterface: RepoInterface) {

    fun fetchUserRepos(userName: String, observer: Observer<List<Repo>>): Subscription {
        return repoInterface.fetchAllRepos(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}
