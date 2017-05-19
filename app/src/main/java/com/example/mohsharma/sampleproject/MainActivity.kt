package com.example.mohsharma.sampleproject

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mohsharma.sampleproject.repositery.DataRepository
import com.example.mohsharma.sampleproject.viewmodel.RepoViewModel
import javax.inject.Inject


class MainActivity : LifecycleActivity() {
    @Inject
    lateinit var dataRepository: DataRepository

    private val repoViewModel: RepoViewModel by lazy {
        RepoViewModel(dataRepository)
    }

    private lateinit var button: Button
    private lateinit var repoNameTextView: TextView
    private lateinit var userName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).repositoryComponent.inject(this)
        button = findViewById(R.id.button) as Button
        repoNameTextView = findViewById(R.id.repo_names) as TextView
        userName = findViewById(R.id.user_name) as EditText

        repoViewModel.getRepos().observe(this, Observer { repoList ->
            var repoNames = ""
            repoList?.forEach { repo ->
                repoNames = repoNames.plus(repo.name).plus(",")
            }
            repoNameTextView.text = "Names of all repos  ${repoNames}"
        })

        button.setOnClickListener {
            repoViewModel.repoObservable.onNext(userName.text.toString())
        }


    }
}
