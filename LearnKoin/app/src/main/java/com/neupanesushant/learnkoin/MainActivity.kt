package com.neupanesushant.learnkoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.neupanesushant.learnkoin.databinding.ActivityMainBinding
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), AndroidScopeComponent {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private val hello by inject<String>(named("hello"))
    // learn to use antoher dependency
//    private val api  = get<MyApi>()
//    private val api by inject<MyApi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("MainViewModel", "$hello")
        viewModel = getViewModel<MainViewModel>()
        viewModel.doNetworkCall()


        //this is just a test to create an instance using koin
//        val retrofit by inject<MyApi>(named("retrofitInstance"))
    }

    override val scope: Scope by activityScope()
}