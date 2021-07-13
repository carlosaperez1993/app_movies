package com.app.movieapp.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.app.movieapp.adapters.CastAdapter
import com.app.movieapp.adapters.GenreAdapter
import com.app.movieapp.delegates.ClickCastDetail
import com.app.movieapp.viewState.DetailViewState
import com.app.movieapp.R
import com.app.movieapp.adapters.SeasonsAdapter
import com.app.movieapp.utils.toast
import com.app.movieapp.data.viewmodels.AppViewModel
import com.app.movieapp.models.Cast
import com.app.movieapp.models.TvSerieDetail
import kotlinx.android.synthetic.main.activity_tvserie_detail.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TvSerieDetailActivity : AppCompatActivity(), ClickCastDetail {

    override fun onTapCast(cast: Cast?) {
        val intent  = Intent(this, ActorDetailActivity::class.java)
        println("castId inside movie detail :"+cast?.id)
        intent.putExtra("cast_id",cast?.id)
        startActivity(intent)
    }

    lateinit var dialog : ProgressDialog

    lateinit var seasonsAdapter: SeasonsAdapter

    lateinit var genreAdapter: GenreAdapter

    lateinit var appViewModel : AppViewModel

    @SuppressLint("WrongConstant")
    fun setupView(tvSerieDetail : TvSerieDetail){

        movie_detail_title.text = HtmlCompat.fromHtml(getString(R.string.movie_title,tvSerieDetail.title),
            Html.FROM_HTML_MODE_LEGACY
        )

        cast_caption.text = HtmlCompat.fromHtml(getString(R.string.season_caption),
            Html.FROM_HTML_MODE_LEGACY
        )

        movie_detail_overview.text = tvSerieDetail.overview

        Glide.with(this@TvSerieDetailActivity)
            .load("https://image.tmdb.org/t/p/w154"+tvSerieDetail.backdrop_path)
            .into(movie_detail_poster)

        movie_detail_screentime.text = ("Duration: " + tvSerieDetail.episode_run_time[0].toString() + " Minutes")
        movie_detail_fav_button.visibility = View.GONE
        genreAdapter.setNewData(tvSerieDetail.genres)

        seasonsAdapter.setNewData(tvSerieDetail.seasons)

    }

    fun render(state : DetailViewState){
        when(state){
            is DetailViewState.MovieDetailViewStateLoading -> loading()
            is DetailViewState.TvSerieDetailViewStateSuccess -> success(state)
            is DetailViewState.MovieDetailViewStateFail -> fail(state)
        }
    }

    fun success(state : DetailViewState.TvSerieDetailViewStateSuccess){
        dialog.hide()
        setupView(state.tvSerieDetail)
    }

    fun loading(){
        dialog.setMessage("Loading...")
        dialog.setCancelable(false)
        dialog.show()
    }

    fun fail(state : DetailViewState.MovieDetailViewStateFail){
        dialog.hide()
        toast(state.message)
    }

    override fun onPause() {
        super.onPause()
        if(dialog.isShowing) dialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(dialog.isShowing) dialog.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvserie_detail)

        genreAdapter = GenreAdapter(this)

        seasonsAdapter = SeasonsAdapter(this)

        genre_rv.adapter = genreAdapter

        casts_rv.adapter = seasonsAdapter

        dialog = ProgressDialog(this@TvSerieDetailActivity)

        val id : Long = intent.getLongExtra("serie_id",0)

        appViewModel = getViewModel<AppViewModel>()

        appViewModel.getTvSerieDetail(id)

        appViewModel.detailViewState.observe(this, Observer {
            render(it)
        })


    }
}
