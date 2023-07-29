package com.neupanesushant.imageretriever.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.neupanesushant.imageretriever.databinding.ItemImagesBinding

class ImageAdapter(
    private val context: Context,
    private var imageList: List<Uri>,
    private val onImageClick: (Uri) -> Unit
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {

        val root = binding.root
        val image = binding.displayImageIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        val animation = AnimationUtils.loadAnimation(
//            context,
//            androidx.appcompat.R.anim.abc_fade_in
//        )
//        animation.duration = 100
//        holder.itemView.animation = animation

        Glide.with(context)
            .asBitmap()
            .load(imageList[position])
            .apply(RequestOptions.centerCropTransform())
            .apply(RequestOptions.overrideOf(450, 480))
            .apply(RequestOptions().transform(RoundedCorners(32)))
            .into(holder.image)

        holder.image.setOnClickListener {
            onImageClick(imageList[position])
        }
    }

    override fun getItemCount(): Int = imageList.size

    @SuppressLint("NotifyDataSetChanged")
    public fun changeList(newList: List<Uri>) {
        imageList = newList
        notifyDataSetChanged()
    }
}