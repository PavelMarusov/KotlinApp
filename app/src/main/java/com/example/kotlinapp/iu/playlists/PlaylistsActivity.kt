package com.example.kotlinapp.iu.playlists

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.App
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.iu.detailplaylist.DetailPlaylistActivity
import com.example.kotlinapp.iu.playlists.adapter.PlaylistAdapters
import com.example.kotlinapp.models.Playlist
import com.example.kotlinapp.models.PlaylistItems
import kotlinx.android.synthetic.main.activity_playlits.*


class PlaylistsActivity : BaseActivity<PlaylistViewModel>(R.layout.activity_playlits) {

    private lateinit var adapter: PlaylistAdapters
    private lateinit var item:Playlist
    private var list: MutableList<Playlist> = mutableListOf()

    override val viewModel: PlaylistViewModel
        get() =ViewModelProvider(this).get(PlaylistViewModel::class.java)

    override fun setupViews() {
       setupAdapter()
    }

    override fun setupLiveData() {

    }

    override fun setupFetchRequests() {
        getData()
        fromDB()
    }

    fun fromDB(){
        var isFirst:Boolean? = true
    App.database.historyDao().getAll()?.observe(this, Observer { playlists ->
        val data = mutableListOf<PlaylistItems>()
       for (i in playlists!!) i.items?.let { data.addAll(it) }
        adapter.addItems(data)


    })
}

    private fun getData() {
        viewModel.fetchPlaylists().observe(this, Observer {
            list.apply {
                if (it != null) {
                    item = it
                    viewModel.getDataFromDB(it)
                    it.items?.let { it1 -> adapter.addItems(it1)  }
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = PlaylistAdapters() { item: PlaylistItems -> onItemClick(item) }
        recycler_playlists.adapter = adapter
    }

    fun onItemClick(item: PlaylistItems) {
        val intent = Intent(this, DetailPlaylistActivity::class.java)
        intent.putExtra("id",item.id)
        startActivity(intent)
    }


}