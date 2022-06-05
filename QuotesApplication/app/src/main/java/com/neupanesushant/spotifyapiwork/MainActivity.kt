package com.neupanesushant.spotifyapiwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.quotesapplication.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


private const val  BASE_URL= "https://favqs.com/api/"
class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var quoteObject : QuoteDate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getContentData()
        binding.btnNext.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                getContentData()
            }

        })
    }

    fun getContentData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(FactsAPI::class.java)


        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object : Callback<QuoteDate>{
            override fun onResponse(call: Call<QuoteDate>, response: Response<QuoteDate>) {
                if(response.body()!=null){
                    Log.i("MainActivity", "Succesfull response with not null Body")
                    quoteObject = response.body()!!
                    Log.i("MainActivity", "${quoteObject.quote.toString()}")
                    val quote = "\" ${quoteObject.quote.body} \""
                    val author = "- ${quoteObject.quote.author}"
                    binding.tvQuote.text = quote
                    binding.tvAuthor.text = author

                }
            }

            override fun onFailure(call: Call<QuoteDate>, t: Throwable) {
                Log.i("MainActivity", "Error response")
            }

        })
    }
}