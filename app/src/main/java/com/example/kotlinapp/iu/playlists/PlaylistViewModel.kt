package com.example.kotlinapp.iu.playlists

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.App
import com.example.kotlinapp.dataBase.DataBaseClient
import com.example.kotlinapp.models.Playlist
import com.example.kotlinapp.repository.YoutubeRepository

class PlaylistViewModel : ViewModel() {
   lateinit var  liveData:MutableLiveData<Playlist?>
    fun fetchPlaylists(): MutableLiveData<Playlist?> {
        return YoutubeRepository().fetchPlaylistsFromNetwork()
    }

    fun getDataFromDB(items:Playlist){
        App.database.historyDao().add(items)


    }
}