package com.example.kotlinapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.models.PlaylistItems

open class BaseViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var playlistItems = MutableLiveData<MutableList<PlaylistItems>>()

}