package com.example.mohsharma.autofillapidemo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.mohsharma.autofillapidemo.model.Repo
import com.example.mohsharma.autofillapidemo.repositery.RepoRepositery
import rx.Observer
import rx.subjects.PublishSubject


class RepoViewModel : ViewModel() {
    companion object {
        private val TAG = RepoViewModel.javaClass.name
    }

    var repoResponse: MutableLiveData<List<Repo>> = MutableLiveData()
    val repoObservable = PublishSubject.create<String>()
    private lateinit var repoRepositery: RepoRepositery

    fun injectService(repoRepositery: RepoRepositery) {
        this.repoRepositery = repoRepositery
    }

    init {
        repoObservable.subscribe { userName ->
            repoRepositery.fetchMovies(userName, object : Observer<List<Repo>> {
                override fun onCompleted() {
                    Log.v(TAG, "On complete called")
                }

                override fun onError(e: Throwable) {
                    Log.v(TAG, "on error called " + e.message)
                }

                override fun onNext(repoList: List<Repo>) {
                    repoResponse.value = repoList
                }
            })
        }
    }

}

