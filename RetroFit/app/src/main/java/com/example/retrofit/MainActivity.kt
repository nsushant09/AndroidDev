package com.example.retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.User
import com.example.retrofit.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    lateinit var todoAdapter : TodoAdapter
    val viewModel : MainViewModel by viewModel<MainViewModel>()
    lateinit var linearLayoutManager: LinearLayoutManager

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.layoutManager = LinearLayoutManager(this)
        searchInputTextListener()
        viewModel.listOfData.observe(this, Observer{
            todoAdapter = TodoAdapter(baseContext, it!!)
            binding.rv.adapter = todoAdapter
            todoAdapter.notifyDataSetChanged()
            Log.i("MainViewModel", "DataSetChanged")
        })


    }

    fun searchInputTextListener(){
        binding.etSearch.addTextChangedListener {

            todoAdapter.filter.filter(it.toString())

        }
    }


}