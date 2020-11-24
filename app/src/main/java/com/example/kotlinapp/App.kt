package com.example.kotlinapp

import android.app.Application
import androidx.room.Room
import com.example.kotlinapp.dataBase.AppDatabase
import com.example.kotlinapp.dataBase.DataBaseClient

class App: Application() {
    companion object{
        lateinit var database:AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,

            "playlist.database"
        ).allowMainThreadQueries().build()

    }
}