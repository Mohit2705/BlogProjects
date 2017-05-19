package com.example.mohsharma.sampleproject.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mohsharma.sampleproject.model.Repo
import com.example.mohsharma.sampleproject.repositery.DataRepository
import rx.subjects.PublishSubject

class RepoViewModel(dataRepository: DataRepository) : ViewModel() {
    private var repoResponse: MutableLiveData<List<Repo>> = MutableLiveData()
    val repoObservable = PublishSubject.create<String>()

    init {
        repoObservable.subscribe(dataRepository.userNameObservable)
        dataRepository.repoResponse.subscribe {
            repoResponse.value = it
        }
    }

    fun getRepos(): MutableLiveData<List<Repo>> {
        return repoResponse
    }

}

