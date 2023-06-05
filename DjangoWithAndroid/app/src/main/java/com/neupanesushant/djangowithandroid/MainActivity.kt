package com.neupanesushant.djangowithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neupanesushant.djangowithandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val endpoints: Endpoints by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupEventListener()
        setupObserver()
    }

    private fun setupView() {
        performRequest()
    }

    private fun setupEventListener() {
    }

    private fun setupObserver() {
    }

    fun performRequest(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val list = fetchData()
                print(list)
            }catch (e : java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
    private suspend fun fetchData(): List<Company> {
        return endpoints.getAllCompanies()
    }
}