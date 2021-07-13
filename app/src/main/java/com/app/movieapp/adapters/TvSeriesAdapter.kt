package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.app.movieapp.R
import com.app.movieapp.delegates.ClickTvSerieDetail
import com.app.movieapp.viewholders.TvSeriesViewHolder
import com.app.movieapp.models.TvSerie

class TvSeriesAdapter(context : Context, clickMovieDetail: ClickTvSerieDetail) : BaseRecyclerAdapter<TvSeriesViewHolder,TvSerie>(context){

    var mCLickDetail : ClickTvSerieDetail = clickMovieDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val view : View = layoutInflater.inflate(R.layout.movie_card,parent,false)
        return TvSeriesViewHolder(view,mCLickDetail)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        holder!!.bind(data!!.get(position))
    }
}