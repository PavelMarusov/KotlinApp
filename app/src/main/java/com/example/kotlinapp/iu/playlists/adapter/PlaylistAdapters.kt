package com.example.kotlinapp.iu.playlists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.iu.loadImage
import com.example.kotlinapp.models.PlaylistItems

class PlaylistAdapters (private var listener:(PlaylistItems) -> Unit) : RecyclerView.Adapter<PlaylistAdapters.PlaylistHolder>() {

    var list: MutableList<PlaylistItems> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_playlists, parent, false)
        return PlaylistHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        list[position].snippet?.thumbnails?.medium?.url?.let {
            holder.image.loadImage(
                it
            )
        }
        holder.title.text = list[position].snippet?.title
        holder.count.text = list[position].contentDetails?.itemCount
        holder.itemView.setOnClickListener{
           listener(list[position])
        }
    }
    fun addItems(item:MutableList<PlaylistItems>){
        this.list = item
        notifyDataSetChanged()
    }


    class PlaylistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image);
        val title: TextView = itemView.findViewById(R.id.playlist_title)
        val count: TextView = itemView.findViewById(R.id.playlist_count)
    }
}