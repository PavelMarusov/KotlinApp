package com.example.kotlinapp.iu.detailplaylist.adaptre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.loadImage
import com.example.kotlinapp.models.PlaylistItems

class DetailPlaylistAdapter (private var listener:(PlaylistItems) -> Unit): RecyclerView.Adapter<DetailPlaylistAdapter.DetailHolder>() {
    var list: MutableList<PlaylistItems> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_detail_playlist, parent, false)
        return DetailHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.imageVideo.loadImage(list[position].snippet?.thumbnails?.medium?.url.toString())
        holder.titleVideo.text=list[position].snippet?.title
        holder.itemView.setOnClickListener{
            listener(list[position])
        }
    }

    fun initList( listItems:MutableList<PlaylistItems>){
        this.list=listItems
    }
    class DetailHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val imageVideo : ImageView = itemView.findViewById(R.id.image_detail)
        val titleVideo: TextView = itemView.findViewById(R.id.detail_title)
        val lengthVideo: TextView = itemView.findViewById(R.id.detail_video_time)

    }
}