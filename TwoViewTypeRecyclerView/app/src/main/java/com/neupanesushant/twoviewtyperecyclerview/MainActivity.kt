package com.neupanesushant.twoviewtyperecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.twoviewtyperecyclerview.databinding.ActivityMainBinding

const val SUSHANT = 1
const val YOGESH = 2
const val UTSAB = 3
const val PRATYUSH = 4

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)
//
        binding.rv.adapter = CustomAdapter(baseContext, getLists())
    }



}