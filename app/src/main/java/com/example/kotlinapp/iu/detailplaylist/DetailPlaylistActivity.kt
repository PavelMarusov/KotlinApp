package com.example.kotlinapp.iu.detailplaylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.kotlinapp.R
import com.example.kotlinapp.iu.detailplaylist.adaptre.DetailPlaylistAdapter
import com.example.kotlinapp.iu.loadImage
import com.example.kotlinapp.iu.playlists.PlaylistViewModel
import com.example.kotlinapp.models.DetailVideo
import kotlinx.android.synthetic.main.activity_detail_playlist.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailPlaylistActivity : AppCompatActivity() {
    var data: String? = null
    private lateinit var viewModel: DetailViewModel
    private lateinit var adapter: DetailPlaylistAdapter
    private var list: MutableList<DetailVideo> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        setContentView(R.layout.activity_detail_playlist)
        comminIntent()
        initAdapter()
        getData(data)
    }

    fun initAdapter() {
        adapter = DetailPlaylistAdapter()
        playLists_recyclerview.adapter = adapter
        val  snap = LinearSnapHelper()
        snap.attachToRecyclerView(playLists_recyclerview)

    }

    fun comminIntent() {
        data = intent.getStringExtra("id")
        Log.d("pop", "playlistID +" + data)
    }

    fun getData(data: String?) {
        viewModel.getPlaylistDetail(data).observe(this, Observer {
            list.apply {
                if (it != null) {
                    it.items?.let { it1 ->
                        adapter.initList(it.items!!)
                        Log.d("pop", " Ok")
                    }
                } else Log.d("pop", " Not Work")

            }
            image_view.loadImage(it?.items?.get(0)?.snippetD?.thumbnailsD?.mediumD?.urlD.toString())
        })
    }
}