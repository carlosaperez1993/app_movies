package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.app.movieapp.R
import com.app.movieapp.models.Seasons
import com.app.movieapp.viewholders.SeasonsViewHolder

class SeasonsAdapter(context: Context) : BaseRecyclerAdapter<SeasonsViewHolder, Seasons>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val view: View = layoutInflater.inflate(R.layout.cast_item, parent, false)
        return SeasonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        holder!!.bind(data!!.get(position))
    }
}