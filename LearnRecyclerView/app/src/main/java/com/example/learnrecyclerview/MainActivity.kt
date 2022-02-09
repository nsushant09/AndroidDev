package com.example.learnrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnrecyclerview.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set the LayoutManager that this RecyclerView will user
        //The Layout is Linear Layout here
        rv.layoutManager = LinearLayoutManager(this)

        //Adapter class is initialized and list is passed in the parameter.
        val itemAdapter = ItemAdapter(this, getItemsList())

        //Adapter instance is set to to the recycler View to inflate the items.
        rv.adapter = itemAdapter
    }

    private fun getItemsList() : ArrayList<String>{
        val list = ArrayList<String>()

        for(i in 1..15){
            list.add("Item $i")
        }

        return list
    }
}