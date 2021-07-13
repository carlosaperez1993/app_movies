package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.app.movieapp.R
import com.app.movieapp.delegates.ClickMovieDetail
import com.app.movieapp.models.FavMovie
import com.app.movieapp.viewholders.FavoriteViewHolder

class FavoritesAdapter(context: Context, clickMovieDetail: ClickMovieDetail) : BaseRecyclerAdapter<FavoriteViewHolder, FavMovie>(context) {

    val mClickMovieDetail : ClickMovieDetail = clickMovieDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view : View = layoutInflater.inflate(R.layout.movie_card,parent,false)
        return FavoriteViewHolder(view, mClickMovieDetail)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder!!.bind(data!!.get(position))
    }
}