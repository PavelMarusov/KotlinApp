package com.example.kotlinapp.iu.playlists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.R

class PlaylistsActivity : AppCompatActivity() {
    private lateinit var viewModel: PlaylistViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlits)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        getData()
    }

    private fun getData(){
        viewModel.fetchPlaylists().observe(this, Observer {

        })
    }
}