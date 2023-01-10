package com.neupanesushant.animationsapplication

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.neupanesushant.animationsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layout.animation = AnimationUtils.loadAnimation(baseContext, R.anim.bounce)
    }

    @SuppressLint("Recycle")
    private fun forLinearRadiusProgressBar(){
        // code for progress bar in xml
//        <ProgressBar
//        android:id="@+id/detailsProgressBar"
//        android:layout_width="match_parent"
//        android:layout_height="4dp"
//        style="@style/Widget.AppCompat.ProgressBar.Horizontal"
//        android:indeterminate="false"
//        android:progressDrawable="@drawable/nabil_nagarik_progress_bar_drawable"
//        android:layout_marginBottom="4dp"
//        />

        //create object animator for animation i.e
        var objectAnimator = ObjectAnimator()

        //for animation
//        objectAnimator = ObjectAnimator.ofInt(viewName, propertyName i.e "progress" , startValue, endValue)
//
//        if(objectAnimator.isRunning){
//            objectAnimator.end()
//        }
//        objectAnimator.duration = 100;
//        objectAnimator.start()
    }
}