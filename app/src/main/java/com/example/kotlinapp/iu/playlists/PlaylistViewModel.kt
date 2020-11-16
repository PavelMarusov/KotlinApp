package com.example.kotlinapp.iu.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.models.Playlist
import com.example.kotlinapp.repository.YoutubeRepository

class PlaylistViewModel : ViewModel() {
    fun fetchPlaylists(): MutableLiveData<Playlist?> {
        return YoutubeRepository().fetchPlaylistsFromNetwork()
    }
}