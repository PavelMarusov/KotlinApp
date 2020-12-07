package com.example.kotlinapp.data.network

import com.example.kotlinapp.models.Playlist
import com.example.kotlinapp.models.PlaylistItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutybeApi {
    @GET("youtube/v3/playlists")
    fun fetchPlaylists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String,
        @Query("maxResult") maxResult: String
    ): Call<Playlist?>



    @GET("youtube/v3/playlistItems")
     fun fetchPlayListsItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String?,
        @Query("pageToken") pageToken: String?
    ): Call<Playlist>
}