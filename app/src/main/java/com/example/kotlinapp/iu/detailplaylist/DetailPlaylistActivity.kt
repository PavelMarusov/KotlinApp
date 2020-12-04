package com.example.kotlinapp.iu.detailplaylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.iu.detailinfo.DetailInfoViewModel
import com.example.kotlinapp.iu.detailinfo.DetailinfoActivity
import com.example.kotlinapp.iu.detailplaylist.adaptre.DetailPlaylistAdapter
import com.example.kotlinapp.iu.loadImage
import com.example.kotlinapp.iu.playlists.PlaylistViewModel
import com.example.kotlinapp.models.DetailVideo
import com.example.kotlinapp.models.PlaylistItems
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_playlist.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailPlaylistActivity : BaseActivity<DetailViewModel> (R.layout.activity_detail_playlist) {
    var data: String? = null
    private lateinit var adapter: DetailPlaylistAdapter
    private var list: MutableList<DetailVideo> = mutableListOf()

    override val viewModel: DetailViewModel
        get() =  ViewModelProvider(this).get(DetailViewModel::class.java)

    override fun setupViews() {
        initAdapter()
        comminIntent()
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    override fun setupFetchRequests() {

    }
    override fun setupLiveData() {
        getData(data)
    }


    fun initAdapter() {
        adapter = DetailPlaylistAdapter() { item: DetailVideo -> onItemClick(item) }
        playLists_recyclerview.adapter = adapter
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(playLists_recyclerview)

    }

    fun onItemClick(item: DetailVideo?) {
        var intent = Intent(this, DetailinfoActivity::class.java)
        intent.putExtra("keyUrl",item?.snippetD?.thumbnailsD?.mediumD?.urlD )
        intent.putExtra("keyTitle",item?.snippetD?.title )
        intent.putExtra("keyDescription",item?.snippetD?.description)
        intent.putExtra("videoId",item?.snippetD?.recourseId?.videoId)
        Log.e("pop", "Item $item")
        intent.putExtra("model",item)
        startActivity(intent)
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