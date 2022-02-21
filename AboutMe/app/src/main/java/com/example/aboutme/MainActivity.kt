package com.example.aboutme


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val myName : MyName = MyName("Sushant")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //pass the class to the data name in the layout file
        binding.myName = myName


        var nickname : String?=null
        binding.btnDone.setOnClickListener{
            addNickname(it)
            binding.invalidateAll()
        }
    }

    private fun addNickname(view : View){
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            //invalidateAll() will refresh the binding.
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            btnDone.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
    }
}