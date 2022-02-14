package com.example.todorecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todorecycler.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    //An app for learning Practising recycler view
    var arrayName : ArrayList<String> = ArrayList<String>()
    var arrayAge : ArrayList<Int> = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //layoutmanager
        rv.layoutManager = LinearLayoutManager(this)

        //recycle view adapter
        var recyclerAdapter : RecyclerAdapter = RecyclerAdapter(this, arrayName, arrayAge)


        btnAdd.setOnClickListener{
            try{
                arrayName.add(editName.text.toString())
                arrayAge.add(editAge.text.toString().toInt())
                recyclerAdapter = RecyclerAdapter(this,arrayName,arrayAge)
                rv.adapter = recyclerAdapter
                Toast.makeText(this, "Sucessfully added ",Toast.LENGTH_SHORT).show()
            }catch (e : Exception){
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }

        //bind adapter to layout
        rv.adapter = recyclerAdapter



    }

    fun arrayNameFill() : ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Sushant")
        list.add("Yogesh")
        list.add("Utsab")

        return list
    }

    fun arrayAgeFill() : ArrayList<Int>{
        val list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)

        return list
    }
}