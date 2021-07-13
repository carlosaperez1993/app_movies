package com.app.movieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.app.movieapp.delegates.ClickMovieDetail
import com.app.movieapp.R
import com.app.movieapp.delegates.ClickTvSerieDetail
import com.app.movieapp.models.Movie
import com.app.movieapp.models.TvSerie
import kotlinx.android.synthetic.main.movie_card.view.*

class TvSeriesViewHolder(itemview : View, movieDetail : ClickTvSerieDetail) : BaseViewHolder<TvSerie>(itemview) {

    val movieDetail : ClickTvSerieDetail? = movieDetail

    var tvSerie : TvSerie? = null

    override fun bind(data: TvSerie) {
        tvSerie = data

        itemView.movie_title.text = data.title

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w300"+data.imgUrl)
            .placeholder(R.drawable.digimon)
            .into(itemView.movie_poster)

        itemView.release_year.text  = data.release_date
        itemView.tv_vote_average.text = data.vote_average

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        movieDetail!!.onTap(tvSerie)
    }
}