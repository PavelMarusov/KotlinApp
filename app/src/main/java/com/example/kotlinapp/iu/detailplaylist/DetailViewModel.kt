package com.example.kotlinapp.iu.detailplaylist

import android.util.MutableLong
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.models.PlayListDetail
import com.example.kotlinapp.repository.YoutubeRepository

class DetailViewModel : ViewModel() {
    fun getPlaylistDetail(data:String?):MutableLiveData<PlayListDetail?>{
       return YoutubeRepository().fetchDetailPlaylist(data)
    }
}