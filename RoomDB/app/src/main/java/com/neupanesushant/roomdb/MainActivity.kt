package com.neupanesushant.roomdb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.addUser(User(0, "Sushant", "Neupane", 19))
        viewModel.readAllData.observe(this, Observer {
            Log.i("MainActivity", "The username is ${viewModel.readAllData.value?.get(0)?.firstName}")
        })
    }
}