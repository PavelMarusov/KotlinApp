package com.example.kotlinapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlinapp.data.network.RetrofitService
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

    fun fetchDetailPlaylist(playlistID: String?): MutableLiveData<Playlist?> {
        val data = MutableLiveData<Playlist?>()
        api.fetchPlayListsItems(key,part,playlistID,null)
            .enqueue(object : Callback<Playlist?> {
                override fun onFailure(call: Call<Playlist?>, t: Throwable) {
                    data.value = null
                    Log.e("kfkfkfskjfksjk", "onFailure: ", t)
                }

                override fun onResponse(call: Call<Playlist?>, response: Response<Playlist?>) {
                    Log.d("lkkkgldfkjtlksjgkksj", "onResponse: ${response.body()}")
                    data.value = response.body()
                }
            })
        return data
    }


}