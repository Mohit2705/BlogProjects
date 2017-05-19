package com.example.mohsharma.autofillapidemo

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mohsharma.autofillapidemo.repositery.RepoRepositery
import com.example.mohsharma.autofillapidemo.viewmodel.RepoViewModel
import javax.inject.Inject


class MainActivity : LifecycleActivity() {
    companion object {
        private val TAG = MainActivity.javaClass.name
    }

    @Inject
    lateinit var repoService: RepoRepositery

    private val repoViewModel: RepoViewModel by lazy {
        val viewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        viewModel.injectService(repoService)
        viewModel
    }

    private lateinit var button: Button
    private lateinit var repoNameTextView: TextView
    private lateinit var userName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).networkComponent.inject(this)
        button = findViewById(R.id.button) as Button
        repoNameTextView = findViewById(R.id.repo_names) as TextView
        userName = findViewById(R.id.user_name) as EditText

        repoViewModel.repoResponse.observe(this, Observer { repoList ->
            Log.v(TAG, "Update the ui ".plus(repoList?.size))
            var repoNames = ""
            repoList?.forEach { repo ->
                repoNames = repoNames.plus(repo.name).plus(",")
            }
            repoNameTextView.text = "Names of all repos ".plus(repoNames)

        })

        button.setOnClickListener {
            repoViewModel.repoObservable.onNext(userName.text.toString())
        }


    }
}
