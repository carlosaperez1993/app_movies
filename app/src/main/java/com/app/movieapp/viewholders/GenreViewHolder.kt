package com.app.movieapp.viewholders

import android.view.View
import com.app.movieapp.models.Genre
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreViewHolder(itemView: View) : BaseViewHolder<Genre>(itemView) {

    var genre : Genre? = null

    override fun bind(data: Genre) {
        //binding with views
        genre = data
        itemView.genre_name.text = genre!!.name
    }
}