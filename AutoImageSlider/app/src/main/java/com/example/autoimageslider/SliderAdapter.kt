package com.example.autoimageslider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.autoimageslider.databinding.SliderItemBinding

class SliderAdapter(
    val viewPager : ViewPager2,
    val imgList : ArrayList<SliderItem>
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){

    inner class SliderViewHolder(binding: SliderItemBinding) : RecyclerView.ViewHolder(binding.root){
        val img = binding.imgSlider
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            SliderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listImg = imgList[position]
        holder.img.setImageResource(listImg.img)
        if(position == imgList.size - 2){
            viewPager.post(run)
        }
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    private val run =Runnable{
            imgList.addAll(imgList)
            notifyDataSetChanged()
    }


}