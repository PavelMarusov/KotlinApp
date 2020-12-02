package com.example.kotlinapp.iu.detailinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.iu.detailplaylist.DetailViewModel
import com.example.kotlinapp.iu.loadImage
import kotlinx.android.synthetic.main.activity_detailinfo.*

class DetailinfoActivity : BaseActivity<DetailInfoViewModel>(R.layout.activity_detailinfo) {
    override val viewModel: DetailInfoViewModel
        get() =  ViewModelProvider(this).get(DetailInfoViewModel::class.java)

    override fun setupViews() {
     getIntentVideo()
    }

    override fun setupLiveData() {

    }

    override fun setupFetchRequests() {

    }
    fun getIntentVideo(){
        val url = intent.getSerializableExtra("keyUrl")
        val title = intent.getSerializableExtra("keyTitle")
        val description = intent.getSerializableExtra("keyDescription")
        image_detail_video_activity.loadImage(url.toString())
        tv_title_detail_video.text = title.toString()
        tv_description_detail_video.text = description.toString()
    }

}