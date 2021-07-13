package com.app.movieapp.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.app.movieapp.adapters.CastAdapter
import com.app.movieapp.adapters.GenreAdapter
import com.app.movieapp.delegates.ClickCastDetail
import com.app.movieapp.viewState.DetailViewState
import com.app.movieapp.R
import com.app.movieapp.utils.toast
import com.app.movieapp.data.viewmodels.AppViewModel
import com.app.movieapp.models.Cast
import com.app.movieapp.models.FavMovie
import com.app.movieapp.models.Movie
import com.app.movieapp.viewState.FavoriteMoviesViewState
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MovieDetailActivity : AppCompatActivity(),ClickCastDetail {

    private lateinit var movie: Movie

    private  var isFavMovie: Boolean = false
    override fun onTapCast(cast: Cast?) {
        val intent  = Intent(this, ActorDetailActivity::class.java)
        println("castId inside movie detail :"+cast?.id)
        intent.putExtra("cast_id",cast?.id)
        startActivity(intent)
    }

    lateinit var dialog : ProgressDialog

    lateinit var castAdapter: CastAdapter

    lateinit var genreAdapter: GenreAdapter

    lateinit var appViewModel : AppViewModel

    @SuppressLint("WrongConstant")
    fun setupView(movie : Movie){

        movie_detail_title.text = HtmlCompat.fromHtml(getString(R.string.movie_title,movie.title),FROM_HTML_MODE_LEGACY)

        cast_caption.text = HtmlCompat.fromHtml(getString(R.string.casts_caption),FROM_HTML_MODE_LEGACY)

        movie_detail_overview.text = movie.overview

        Glide.with(this@MovieDetailActivity)
            .load("https://image.tmdb.org/t/p/w154"+movie.imgUrl)
            .into(movie_detail_poster)

        movie_detail_screentime.text = ("Duration: " + movie.runtime.toString() + " Minutes")

        genreAdapter.setNewData(movie.genres)

        castAdapter.setNewData(movie.credits?.cast)

    }

    fun render(state : DetailViewState){
        when(state){
             is DetailViewState.MovieDetailViewStateLoading -> loading()
             is DetailViewState.MovieDetailViewStateSuccess -> success(state)
             is DetailViewState.MovieDetailViewStateFail -> fail(state)
        }
    }

    fun success(state : DetailViewState.MovieDetailViewStateSuccess){
        dialog.hide()
        movie = state.movie
        appViewModel.existAsFavorite(movie.movieId)
        appViewModel.favoriteMoviesViewState.observe(this, Observer {
            renderExistAsFavorite(it)
        })

        setupView(state.movie)
    }

    private fun renderExistAsFavorite(state: FavoriteMoviesViewState?) {
        when(state){
            is FavoriteMoviesViewState.FavoriteMovieViewStateLoading -> {}
            is FavoriteMoviesViewState.FavoriteMovieExistViewStateSuccess -> successExistAsFavorite(state)
            is FavoriteMoviesViewState.FavoriteMovieViewStateFail -> {}
        }
    }

    private fun successExistAsFavorite(state: FavoriteMoviesViewState.FavoriteMovieExistViewStateSuccess) {
        isFavMovie = state.exist
        movie_detail_fav_button.isChecked = state.exist
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
        setContentView(R.layout.activity_movie_detail)

        genreAdapter = GenreAdapter(this)

        castAdapter = CastAdapter(this,this)

        genre_rv.adapter = genreAdapter

        casts_rv.adapter = castAdapter

        dialog = ProgressDialog(this@MovieDetailActivity)

        val id : Long = intent.getLongExtra("movie_id",0)

        appViewModel = getViewModel<AppViewModel>()

        appViewModel.getMovieDetail(id)

        appViewModel.detailViewState.observe(this, Observer {
            render(it)
        })

        movie_detail_fav_button.setOnClickListener(View.OnClickListener {
            if (!isFavMovie)
            appViewModel.saveFavorite(FavMovie( movieId =movie.movieId,
                 adult =movie.adult,
                 imgUrl =movie.imgUrl,
             runtime =movie.runtime,
             title =movie.title,
             overview =movie.overview,
             release_date =movie.release_date,
             vote_average =movie.vote_average ))
            else {appViewModel.deleteFavorite(movie.movieId)
            isFavMovie = false}
        })
    }
}
