package com.example.mohsharma.sampleproject.repositery

import android.util.Log
import com.example.mohsharma.sampleproject.model.Repo
import com.example.mohsharma.sampleproject.service.RepoService
import rx.Observer
import rx.subjects.PublishSubject

class DataRepository(private val repoService: RepoService) {
    companion object {
        private val TAG = DataRepository.javaClass.name
    }

    var repoResponse = PublishSubject.create<List<Repo>>()
    val userNameObservable = PublishSubject.create<String>()

    init {
        userNameObservable.subscribe { userName ->
            repoService.fetchUserRepos(userName, object : Observer<List<Repo>> {
                override fun onCompleted() {
                    Log.v(TAG, "On complete called")
                }

                override fun onError(e: Throwable) {
                    Log.v(TAG, "on error called " + e.message)
                }

                override fun onNext(repoList: List<Repo>) {
                    repoResponse.onNext(repoList)
                }
            })

        }
    }
}
