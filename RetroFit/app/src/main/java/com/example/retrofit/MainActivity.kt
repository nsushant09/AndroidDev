package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_card.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"


class MainActivity : AppCompatActivity() {

    lateinit var todoAdapter : TodoAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        rv.layoutManager = LinearLayoutManager(this)
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
                    rv.adapter = todoAdapter
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