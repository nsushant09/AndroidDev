package com.example.colormyviewsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.colormyviewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }
    private fun setListeners() {
        val clickableViews : List<View> = listOf(binding.boxOneText,binding.boxTwoText,binding.boxThreeText,binding.boxFourText,binding.boxFiveText,binding.redButton,binding.yellowButton,binding.greenButton)
        for (item in clickableViews){
            item.setOnClickListener{
                makeColoured(it)
            }
        }
    }

    private fun makeColoured(view : View){
        when(view.id){
            R.id.boxOneText -> view.setBackgroundColor(Color.DKGRAY)
            R.id.boxTwoText -> view.setBackgroundColor(Color.GRAY)
            R.id.boxThreeText -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.boxFourText -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.boxFiveText -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.redButton -> view.setBackgroundColor(resources.getColor(R.color.red))
            R.id.yellowButton -> view.setBackgroundColor(resources.getColor(R.color.yellow))
            R.id.greenButton -> view.setBackgroundColor(resources.getColor(R.color.green))

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}