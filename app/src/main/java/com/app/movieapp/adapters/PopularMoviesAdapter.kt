package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.app.movieapp.delegates.ClickMovieDetail
import com.app.movieapp.R
import com.app.movieapp.viewholders.MoviesViewHolder
import com.app.movieapp.models.Movie

class PopularMoviesAdapter(context: Context,clickMovieDetail: ClickMovieDetail) : BaseRecyclerAdapter<MoviesViewHolder, Movie>(context) {

    val mClickMovieDetail : ClickMovieDetail = clickMovieDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view : View = layoutInflater.inflate(R.layout.movie_card,parent,false)
        return MoviesViewHolder(view, mClickMovieDetail)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
      holder!!.bind(data!!.get(position))
    }
}