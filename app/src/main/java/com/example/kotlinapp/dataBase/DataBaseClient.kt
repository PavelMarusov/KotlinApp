package com.example.kotlinapp.dataBase

import android.content.Context
import androidx.room.Room

class DataBaseClient {
    internal fun providerDatabase(context: Context):AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "playlist.database"
        ).build()
    }

    fun providerHistoryDao(appDatabase: AppDatabase):PlaylistDao?=appDatabase.historyDao()

}