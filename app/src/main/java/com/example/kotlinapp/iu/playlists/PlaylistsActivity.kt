package com.example.kotlinapp.iu.playlists

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.R
import com.example.kotlinapp.iu.detailplaylist.DetailPlaylistActivity
import com.example.kotlinapp.iu.playlists.adapter.PlaylistAdapters
import com.example.kotlinapp.models.Playlist
import com.example.kotlinapp.models.PlaylistItems
import kotlinx.android.synthetic.main.activity_playlits.*


class PlaylistsActivity : AppCompatActivity() {
    private lateinit var viewModel: PlaylistViewModel
    private lateinit var adapter: PlaylistAdapters
    private var list: MutableList<Playlist> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlits)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        setupAdapter()
        getData()

    }

    private fun getData() {
        viewModel.fetchPlaylists().observe(this, Observer {
            list.apply {
                if (it != null) {
                    it.items?.let { it1 -> adapter.addItems(it1) }
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