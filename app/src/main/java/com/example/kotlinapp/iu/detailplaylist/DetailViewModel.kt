package com.example.kotlinapp.iu.detailplaylist

import androidx.lifecycle.MutableLiveData
import com.example.kotlinapp.base.BaseViewModel
import com.example.kotlinapp.models.Playlist
import com.example.kotlinapp.repository.YoutubeRepository

class DetailViewModel : BaseViewModel() {
    fun getPlaylistDetail(data: String?): MutableLiveData<Playlist?> {
        return YoutubeRepository().fetchDetailPlaylist(data)
    }
}