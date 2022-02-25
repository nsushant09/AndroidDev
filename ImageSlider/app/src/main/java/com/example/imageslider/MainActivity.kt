package com.example.imageslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.imageslider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding ?= null
    private val binding get() = _binding!!

    private lateinit var imgList : ArrayList<SliderItem>
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRun : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sliderItems()
        itemSliderView()
    }
    private fun sliderItems() {
        imgList = ArrayList()
        sliderAdapter = SliderAdapter(binding.viewPagerImgSlider, imgList)
        binding.viewPagerImgSlider.adapter = sliderAdapter
        binding.viewPagerImgSlider.clipToPadding = false
        binding.viewPagerImgSlider.clipChildren = false
        binding.viewPagerImgSlider.offscreenPageLimit = 3
        binding.viewPagerImgSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r : Float = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f

        }
        binding.viewPagerImgSlider.setPageTransformer(comPosPageTarn)
        sliderHandler = Handler()
        sliderRun = Runnable {
            binding.viewPagerImgSlider.currentItem = binding.viewPagerImgSlider.currentItem + 1

        }
        binding.viewPagerImgSlider.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRun)
                    sliderHandler.postDelayed(sliderRun,2000)
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRun)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRun,2000)

    }

    private fun itemSliderView() {
        imgList.add(SliderItem(R.drawable.car))
        imgList.add(SliderItem(R.drawable.lrilogo))
        imgList.add(SliderItem(R.drawable.lrilogo))
    }
}