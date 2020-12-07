package com.example.kotlinapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class Playlist(
    @PrimaryKey (autoGenerate = true)
    var id: Long? = null,
    var kind: String? = null,
    var etag: String? = null,
    var items: MutableList<PlaylistItems>? = null
):Serializable

data class PlaylistItems(
    var kind: String? = null,
    var etag: String? = null,
    var id: String? = null,
    var snippet: Snippet? = null,
    var contentDetails: ContentDetails? = null,
    var pageInfo: PageInfo? = null
):Serializable

data class Snippet(
    var publishedAt: String? = null,
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null,
    var resourceId: RecourseId? = null
):Serializable

data class Thumbnails(
    var medium: Medium? = null
):Serializable

data class Medium(
    var url: String? = null
):Serializable
data class ContentDetails(
    var itemCount: String? = null
):Serializable
data class PageInfo(
    var totalResults: String? = null,
    var resultsPerPage: String? = null
):Serializable

data class RecourseId(
    var kind : String? = null,
    var videoId: String? = null
):Serializable