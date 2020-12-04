package com.example.kotlinapp.iu.detailinfo


import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.iu.dialogfragmment.DialogFragment

import com.example.kotlinapp.models.DetailVideo

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detailinfo.*

class DetailinfoActivity : BaseActivity<DetailInfoViewModel>(R.layout.activity_detailinfo) {
    var id:String? = null

    var listModel:MutableList<DetailVideo> = mutableListOf()

    override val viewModel: DetailInfoViewModel
        get() =  ViewModelProvider(this).get(DetailInfoViewModel::class.java)

    override fun setupViews() {
     getIntentVideo()
        playerYT(id)
        toggleFullScreen()
        val addPhotoBottomDialogFragment = DialogFragment.newIns(listModel)
        addPhotoBottomDialogFragment?.show(
            supportFragmentManager,
            DialogFragment.TAG)
    }
    fun playerYT(videoId: String?) {
        val youTubePlayerView =
            findViewById<YouTubePlayerView>(R.id.youtube_player)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                Log.e("www","$videoId")
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0F)
                }
            }
        })
    }

    override fun setupLiveData() {

    }

    override fun setupFetchRequests() {

    }
    fun getIntentVideo(){
        id = intent.getStringExtra("videoId")
        var modelVideo = intent.getSerializableExtra("item") as? DetailVideo
        val url = intent.getSerializableExtra("keyUrl")
        val title = intent.getSerializableExtra("keyTitle")
        val description = intent.getSerializableExtra("keyDescription")
        if (modelVideo != null) {
            listModel.add(modelVideo) }
        tv_title_detail_video.text = title.toString()
        tv_description_detail_video.text = description.toString()
    }

}