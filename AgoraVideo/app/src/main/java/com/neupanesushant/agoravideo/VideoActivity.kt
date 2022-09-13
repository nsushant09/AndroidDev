package com.neupanesushant.agoravideo

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.neupanesushant.agoravideo.databinding.ActivityMainBinding
import com.neupanesushant.agoravideo.databinding.ActivityVideoBinding
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import java.lang.Exception

class VideoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVideoBinding
    private var mRtcEngine : RtcEngine? = null
    private var channelName : String? = null
    private var userRole = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        channelName = intent.getStringExtra("ChannelName")
        userRole = intent.getIntExtra("UserRole", -1)
        initAgoraEngineAndJoinChannel()

        binding.btnMic.setOnClickListener {
            onLocalAudioMuteClicked()
        }

        binding.btnCamera.setOnClickListener {
            onSwitchCameraClicked()
        }

        binding.btnEndCall.setOnClickListener {
            onEndCallClicked()
        }
    }

    fun initAgoraEngineAndJoinChannel(){
        initializeAgoraEngine()

        mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine!!.setClientRole(userRole)
        mRtcEngine!!.enableVideo()
        if(userRole == 1){
            setupLocalVideo()
        }else{
//            binding.localVideoViewContainer.visibility = View.INVISIBLE
            binding.localVideoViewContainer.isVisible = false
        }

        joinChannel()
    }


    private val mRtcEventHandler : IRtcEngineEventHandler = object : IRtcEngineEventHandler(){
        override fun onUserJoined(uid: Int, elapsed: Int) {
            runOnUiThread{setupRemoteVideo(uid)}
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            runOnUiThread{onRemoteUserLeft()}
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            runOnUiThread{ println("Join channel success" + uid)}
        }
    }

    private fun initializeAgoraEngine(){
        try{
            mRtcEngine = RtcEngine.create(baseContext, APP_ID, mRtcEventHandler)
        }catch(e :Exception){
            print("Exception" + e.message)
        }
    }

    private fun setupLocalVideo(){
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        surfaceView.setZOrderMediaOverlay(true)
        binding.localVideoViewContainer.addView(surfaceView)
        mRtcEngine!!.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))
    }

    private fun joinChannel(){
        //0 as uid will allow agora to assign uid of it's own
        mRtcEngine!!.joinChannel(token, channelName, null, 0)
    }

    private fun setupRemoteVideo(uid:Int){
        if(binding.remoteVideoViewContainer.childCount >= 1){
            return
        }

        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        binding.remoteVideoViewContainer.addView(surfaceView)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid))
        surfaceView.tag = uid
    }

    private fun onRemoteUserLeft(){
        binding.remoteVideoViewContainer.removeAllViews()
    }

    @SuppressLint("ResourceType")
    private fun onLocalAudioMuteClicked(){
        val iv = binding.btnMic as ImageView
        if(iv.isSelected){
            iv.isSelected = false
            iv.clearColorFilter()
        }else{
            iv.isSelected = true
            iv.setColorFilter(resources.getColor(Color.RED),PorterDuff.Mode.MULTIPLY)
        }

        mRtcEngine!!.muteLocalAudioStream(iv.isSelected)
    }

    private fun onSwitchCameraClicked(){
        mRtcEngine!!.switchCamera()
    }

    fun onEndCallClicked(){
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mRtcEngine!!.leaveChannel()
        RtcEngine.destroy()
        mRtcEngine = null
    }
}