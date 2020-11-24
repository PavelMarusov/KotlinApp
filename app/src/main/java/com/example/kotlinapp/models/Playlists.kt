package com.example.kotlinapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Playlist(
    @PrimaryKey (autoGenerate = true)
    var id: Long? = null,
    var kind: String? = null,
    var etag: String? = null,
    var items: MutableList<PlaylistItems>? = null
)

data class PlaylistItems(
    var kind: String? = null,
    var etag: String? = null,
    var id: String? = null,
    var snippet: Snippet? = null,
    var contentDetails: ContentDetails? = null
)

data class Snippet(
    var publishedAt: String? = null,
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: Thumbnails? = null
)

data class Thumbnails(
    var medium: Medium? = null
)

data class Medium(
    var url: String? = null
)

data class ContentDetails(
    var itemCount: String? = null
)