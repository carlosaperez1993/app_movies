package com.app.movieapp.viewholders

import android.annotation.SuppressLint
import android.view.View
import com.app.movieapp.R
import com.app.movieapp.models.Seasons
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cast_item.view.*

class SeasonsViewHolder (itemView: View) : BaseViewHolder<Seasons>(itemView),
    View.OnClickListener {

    var seasons : Seasons? = null

    @SuppressLint("WrongConstant", "StringFormatInvalid")
    override fun bind(data: Seasons) {
        this.seasons = data

        itemView.cast_character_name.text = data.name
        itemView.episodes.text = (itemView.context.getString(R.string.episodes_caption)+ data.episode_count.toString())

        Glide.with(itemView.context)
            .load("http://image.tmdb.org/t/p/w300"+data.poster_path)
            .placeholder(R.drawable.digimon)
            .into(itemView.cast_poster)
    }
}