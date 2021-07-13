package com.app.movieapp.viewholders

import android.view.View
import com.app.movieapp.R
import com.app.movieapp.delegates.ClickMovieDetail
import com.app.movieapp.models.FavMovie
import com.app.movieapp.models.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_card.view.*

class MoviesViewHolder(itemView: View, movieDetail: ClickMovieDetail) : BaseViewHolder<Movie>(
    itemView
) {

    var movie : Movie? = null

    var clickMovieDetail : ClickMovieDetail = movieDetail

    override fun bind(data: Movie) {
       //bindings
        this.movie = data

        itemView.movie_title.text = data.title

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w300" + data.imgUrl)
            .placeholder(R.drawable.digimon)
            .into(itemView.movie_poster)

        itemView.release_year.text  = data.release_date

        itemView.tv_vote_average.text = data.vote_average

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        clickMovieDetail.onTap(movie)
    }

}
