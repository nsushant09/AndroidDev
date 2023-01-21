package com.neupanesushant.broadcastrecieverbasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.LiveFolders.INTENT
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.neupanesushant.broadcastrecieverbasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To recieve intent
        val intent = intent
        val action = intent.action
        val type = intent.type

        if(Intent.ACTION_SEND.equals(action) && type != null){
            // for image extra stream
            //for text extra text
            if(type.equals("text/plain")){
                binding.recievedTextTv.text = intent.getStringExtra(Intent.EXTRA_TEXT).toString()
            }else{
                binding.recievedImageTv.setImageURI(intent.getParcelableExtra(Intent.EXTRA_STREAM))
            }
        }
    }
}

/**
 * Notes
 * Broadcast Types
 * System -> Application
 * Application -> Application
 * Same Application -> Same Application
 *
 * Types :
 * Normal Broadcast -> runs on main thread / can't run for long time
 * Ordered Broadcast -> runs with priority of process
 * Sticky Broadcast ->  Deprecated
 * Local Broadcast -> Used within the application
 *
 * Types of implementation :
 * Dynamic Broadcast (Temporary)
 * Static Broadcast (Permanent)
 */
