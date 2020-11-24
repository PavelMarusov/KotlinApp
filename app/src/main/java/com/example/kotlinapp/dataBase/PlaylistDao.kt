package com.example.kotlinapp.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinapp.models.Playlist

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun insert(playlistItems: Playlist): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(playlistItems: Playlist)


    @Query("SELECT*FROM playList")
    fun getAll(): LiveData<List<Playlist>?>?
}