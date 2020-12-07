package com.example.kotlinapp.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinapp.convecters.ClassTypeConverter
import com.example.kotlinapp.models.Playlist

@Database(entities = [Playlist::class],version = 1)
@TypeConverters(ClassTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun historyDao(): PlaylistDao

}