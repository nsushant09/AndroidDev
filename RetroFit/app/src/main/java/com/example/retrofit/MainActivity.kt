package com.example.retrofit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

//Base url of api
const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var todoAdapter : TodoAdapter
    lateinit var viewModel : MainViewModel
    lateinit var linearLayoutManager: LinearLayoutManager
    var listFromResponse = ArrayList<Todo>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.layoutManager = LinearLayoutManager(this)
        viewModel.listOfData.observe(this, Observer{
            todoAdapter = TodoAdapter(baseContext, it!!)
            binding.rv.adapter = todoAdapter
            todoAdapter.notifyDataSetChanged()
            Log.i("MainViewModel", "DataSetChanged")
        })

    }

}