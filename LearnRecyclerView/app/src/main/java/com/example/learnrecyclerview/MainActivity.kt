package com.example.learnrecyclerview

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnrecyclerview.adapters.ItemAdapter
import com.example.learnrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set the LayoutManager that this RecyclerView will user
        //The Layout is Linear Layout here
        binding.rv.layoutManager = LinearLayoutManager(this)

        //Adapter class is initialized and list is passed in the parameter.
        val itemAdapter = ItemAdapter(this, getItemsList(),getImageList())

        //Adapter instance is set to to the recycler View to inflate the items.
        binding.rv.adapter = itemAdapter
    }

    private fun getItemsList() : ArrayList<String>{
        val list = ArrayList<String>()

        for(i in 1..15){
            list.add("Item $i")
        }

        return list
    }

    private fun getImageList():ArrayList<Int>{
        val list = arrayListOf<Int>()
        var i = 0;
        while(i<15){
            list.add(R.drawable.car)
            i++
            list.add(R.drawable.lrilogo)
            i++
            list.add(R.drawable.ic_launcher_background)
            i++
        }
        return list
    }
}