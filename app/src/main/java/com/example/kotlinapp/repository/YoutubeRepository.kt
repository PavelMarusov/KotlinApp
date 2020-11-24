package com.example.kotlinapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlinapp.App
import com.example.kotlinapp.data.network.RetrofitService
import com.example.kotlinapp.models.DetailVideo
import com.example.kotlinapp.models.PlayListDetail
import com.example.kotlinapp.models.Playlist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubeRepository {
    val channel = "UCKcyqRWn4WGqv5ZfRXLzCwA"
    val key = "AIzaSyDgKrLdvydyhndHzT90PPbjJS7hMkxfMFw"
    val part = "snippet,contentDetails"
    val maxResult = "20"


    private var api = RetrofitService().instanceRetrofit()

    fun fetchPlaylistsFromNetwork(): MutableLiveData<Playlist?> {
        val data = MutableLiveData<Playlist?>()
        api.fetchPlaylists(part, key, channel, maxResult).enqueue(object : Callback<Playlist?> {
            override fun onFailure(call: Call<Playlist?>, t: Throwable) {
                data.value = null

            }

            override fun onResponse(call: Call<Playlist?>, response: Response<Playlist?>) {
                data.value = response.body()
                Log.d("pop", "ok " + response.body())
            }

        })
        return data
    }

    fun fetchDetailPlaylist(playlistID: String?): MutableLiveData<PlayListDetail?> {
        val data = MutableLiveData<PlayListDetail?>()
        api.fetchPlayListsItems(key,part,playlistID,null)
            .enqueue(object : Callback<PlayListDetail?> {
                override fun onFailure(call: Call<PlayListDetail?>, t: Throwable) {
                    data.value = null
                    Log.d("pop", "error" + t.message)
                }

                override fun onResponse(call: Call<PlayListDetail?>, response: Response<PlayListDetail?>) {
                    data.value = response.body()
                    Log.d("pop", "ok " + response.body())
                }
            })
        return data
    }


}