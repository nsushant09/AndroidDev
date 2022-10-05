package com.example.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.vm.MainViewModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var todoAdapter: TodoAdapter

//        val viewModel : MainViewModel by viewModels<MainViewModel>()
    val viewModel : MainViewModel by inject()
//    lateinit var viewModel: MainViewModel
    lateinit var linearLayoutManager: LinearLayoutManager

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)


        binding.rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.layoutManager = LinearLayoutManager(this)
        searchInputTextListener()
        viewModel.listOfData.observe(this, Observer {
            todoAdapter = TodoAdapter(baseContext, it!!)
            binding.rv.adapter = todoAdapter
            todoAdapter.notifyDataSetChanged()
            Log.i("MainViewModel", "DataSetChanged")
        })

//
//        binding.refreshLayout.setOnRefreshListener {
//
//            onStop()
//            onRestart()
//            onResume()
//
//            binding.refreshLayout.isRefreshing = false
//        }
    }

    fun searchInputTextListener() {
        binding.etSearch.addTextChangedListener {

            todoAdapter.filter.filter(it.toString())

        }
    }


}