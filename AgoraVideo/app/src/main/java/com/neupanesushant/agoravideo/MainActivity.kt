package com.neupanesushant.agoravideo

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.neupanesushant.agoravideo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermission()
        binding.btnJoinChannel.setOnClickListener {
            onSubmit()
        }
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),22)
    }
    fun onSubmit(){
        val channelName = binding.channel.text.toString()
        val checkedRole= binding.radioGroup.checkedRadioButtonId
        val userRole = if(checkedRole == binding.radioAudience.id){
            0
        }else{
            1
        }

        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("ChannelName", channelName)
        intent.putExtra("UserRole", userRole)
        startActivity(intent)
    }
}