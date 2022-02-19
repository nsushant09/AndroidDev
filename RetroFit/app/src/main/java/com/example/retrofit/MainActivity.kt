package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

//Base url of api
const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var todoAdapter : TodoAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.layoutManager = LinearLayoutManager(this)
        getMyData()



    }

    private fun getMyData(){
        //create an object of retrofitToApi class
        val retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TodoApi::class.java)

        val retrofitData = retrofit.getData()

        retrofitData.enqueue(object : Callback<List<Todo>?> {
            override fun onResponse(call: Call<List<Todo>?>, response: Response<List<Todo>?>) {
                if(response.body()!=null){
                    val responseList  = response.body()!!
                    todoAdapter = TodoAdapter(baseContext, responseList)
//                    todoAdapter.notifyDataSetChanged()
                    binding.rv.adapter = todoAdapter
//                    val todoAdapter = TodoAdapter(baseContext, responseList)
//                    rv.adapter=todoAdapter
                }
            }

            override fun onFailure(call: Call<List<Todo>?>, t: Throwable) {
                Toast.makeText(baseContext, "Could not load data",Toast.LENGTH_SHORT).show()
            }
        })
    }
}