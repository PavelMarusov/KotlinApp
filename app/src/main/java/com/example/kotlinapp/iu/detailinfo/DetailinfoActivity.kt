package com.example.kotlinapp.iu.detailinfo


import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.iu.bottom_sheet.DialogFragment
import com.example.kotlinapp.models.PlaylistItems
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detailinfo.*

class DetailinfoActivity : BaseActivity<DetailInfoViewModel>(R.layout.activity_detailinfo) {
    var id: String? = null
    var listModel: MutableList<PlaylistItems> = mutableListOf()
    override val viewModel: DetailInfoViewModel
        get() = ViewModelProvider(this).get(DetailInfoViewModel::class.java)

    override fun setupViews() {
        getIntentVideo()
        playerYT(id)
        toggleFullScreen()
        val addPhotoBottomDialogFragment = DialogFragment.newIns(listModel)
        addPhotoBottomDialogFragment?.show(supportFragmentManager, DialogFragment.TAG)
    }

    private fun playerYT(videoId: String?) {
        val youTubePlayerView =
            findViewById<YouTubePlayerView>(R.id.youtube_player)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                Log.e("www", "$videoId")
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0F)
                }
            }
        })
    }

    override fun setupLiveData() {}
    override fun setupFetchRequests() {}
    private fun getIntentVideo() {
        listModel.add(intent.getSerializableExtra("list") as PlaylistItems)
        id = intent.getStringExtra("videoId")
        val title = intent.getSerializableExtra("keyTitle")
        val description = intent.getSerializableExtra("keyDescription")
        tv_title_detail_video.text = title.toString()
        tv_description_detail_video.text = description.toString()
    }

}