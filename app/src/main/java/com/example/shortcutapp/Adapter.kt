package com.example.shortcutapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (
        private val context: Context,
        private val comics: List<Comic>) : RecyclerView.Adapter<Adapter.ViewHodler>() {

            private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHodler {
        val itemView = layoutInflater.inflate(R.layout.carditem, parent, false)
        return ViewHodler(itemView)
    }



    override fun onBindViewHolder(holder: Adapter.ViewHodler, position: Int) {
        val comic = comics[position]

        val comicDesc = comic.description
        val comicNum = comic.number
        val comicDetail = comic.detail
        val comicImg = comic.image
        val comicTitle = comic.title

        holder.comicTitle.text = comicTitle


        //click on comic and see desc etc (send intent and start activity)
        holder.itemView.setOnClickListener {

        }
    }

    inner class ViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comicTitle: TextView = itemView.findViewById(R.id.comic_title)
        val comicPic: ImageView = itemView.findViewById(R.id.comic_image)
    }

    override fun getItemCount() = comics.size
}