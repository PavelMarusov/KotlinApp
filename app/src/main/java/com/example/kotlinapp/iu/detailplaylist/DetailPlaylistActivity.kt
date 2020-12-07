package com.example.kotlinapp.iu.detailplaylist

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.iu.detailinfo.DetailinfoActivity
import com.example.kotlinapp.iu.detailplaylist.adaptre.DetailPlaylistAdapter
import com.example.kotlinapp.loadImage
import com.example.kotlinapp.showSnackBar
import com.example.kotlinapp.models.PlaylistItems
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_detail_playlist.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailPlaylistActivity : BaseActivity<DetailViewModel> (R.layout.activity_detail_playlist) {
    var data: String? = null
    private lateinit var adapter: DetailPlaylistAdapter
    private var list: MutableList<PlaylistItems> = mutableListOf()

    override val viewModel: DetailViewModel
        get() =  ViewModelProvider(this).get(DetailViewModel::class.java)

    override fun setupViews() {
        initAdapter()
        commingIntent()
//        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        /*findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
           view.showSnackBar(this,"Replace with your own action")
        }*/
    }
    override fun setupFetchRequests() {}
    override fun setupLiveData() {
        getData(data)
    }


   private fun initAdapter() {
        adapter = DetailPlaylistAdapter() { item: PlaylistItems -> onItemClick(item) }
        playLists_recyclerview.adapter = adapter
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(playLists_recyclerview)
    }

    private fun onItemClick(item: PlaylistItems?) {
        var intent = Intent(this, DetailinfoActivity::class.java)
        intent.putExtra("keyUrl",item?.snippet?.thumbnails?.medium?.url )
        intent.putExtra("keyTitle",item?.snippet?.title )
        intent.putExtra("keyDescription",item?.snippet?.description)
        intent.putExtra("videoId",item?.snippet?.resourceId?.videoId)
        intent.putExtra("list",item)
        startActivity(intent)
    }

    private fun commingIntent() {
        data = intent.getStringExtra("id")
        Log.d("pop", "playlistID +" + data)
    }

    private fun getData(data: String?) {
        viewModel.getPlaylistDetail(data).observe(this, Observer {
            list.apply {
                if (it != null) {
                    it.let { it1 ->
                        adapter.initList(it.items!!)
                        Log.d("pop", " Ok")
                    }
                } else Log.d("pop", " Not Work")

            }
            image_view.loadImage(it?.items?.get(0)?.snippet?.thumbnails?.medium?.url.toString())
        })
    }








}