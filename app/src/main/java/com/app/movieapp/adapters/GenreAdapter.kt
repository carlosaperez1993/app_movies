package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.app.movieapp.R
import com.app.movieapp.viewholders.GenreViewHolder
import com.app.movieapp.models.Genre

class GenreAdapter(context : Context) : BaseRecyclerAdapter<GenreViewHolder,Genre>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view : View = layoutInflater.inflate(R.layout.genre_item,parent,false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder!!.bind(data!!.get(position))
    }
}