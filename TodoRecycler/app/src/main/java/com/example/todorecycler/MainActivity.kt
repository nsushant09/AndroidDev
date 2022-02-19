package com.example.todorecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todorecycler.adapter.RecyclerAdapter
import com.example.todorecycler.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    //An app for learning Practising recycler view
    var arrayName : ArrayList<String> = ArrayList<String>()
    var arrayAge : ArrayList<Int> = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //layoutmanager
        binding.rv.layoutManager = LinearLayoutManager(this)

        //recycle view adapter
        var recyclerAdapter : RecyclerAdapter = RecyclerAdapter(this, arrayName, arrayAge)


        binding.btnAdd.setOnClickListener{
            try{
                arrayName.add(binding.editName.text.toString())
                arrayAge.add(binding.editAge.text.toString().toInt())
                recyclerAdapter = RecyclerAdapter(this,arrayName,arrayAge)
                binding.rv.adapter = recyclerAdapter
                Toast.makeText(this, "Sucessfully added ",Toast.LENGTH_SHORT).show()
            }catch (e : Exception){
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }

        //bind adapter to layout
        binding.rv.adapter = recyclerAdapter



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