package com.app.movieapp.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.app.movieapp.R
import com.app.movieapp.viewState.CastDetailViewState
import com.app.movieapp.adapters.PopularMoviesAdapter
import com.app.movieapp.data.viewmodels.AppViewModel
import com.app.movieapp.delegates.ClickMovieDetail
import com.app.movieapp.utils.toast
import com.app.movieapp.models.Cast
import com.app.movieapp.models.Movie
import kotlinx.android.synthetic.main.activity_actor_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ActorDetailActivity : AppCompatActivity(),ClickMovieDetail{

    lateinit var appViewModel: AppViewModel

    lateinit var movieAdapter : PopularMoviesAdapter

    lateinit var dialog : ProgressDialog

    override fun onTap(movie: Movie?) {
        val intent = Intent(this,
            MovieDetailActivity::class.java)
        intent.putExtra("movie_id",movie?.movieId)
        startActivity(intent)
    }

    @SuppressLint("WrongConstant")
    fun setupView(cast: Cast?){

        Glide.with(this@ActorDetailActivity)
            .load("http://image.tmdb.org/t/p/w300"+cast?.profilePath)
            .into(actor_profile)

        actor_name.text = HtmlCompat.fromHtml(getString(R.string.actor_name,cast?.name),FROM_HTML_MODE_LEGACY)

        about_actor.text = cast?.bio

        movieAdapter.setNewData(cast?.movie_credits?.known_movies)

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
        setContentView(R.layout.activity_actor_detail)

        known_for_caption.text = HtmlCompat.fromHtml(getString(R.string.known_for_caption), Html.FROM_HTML_MODE_LEGACY)

        dialog = ProgressDialog(this@ActorDetailActivity)

        appViewModel = getViewModel<AppViewModel>()

       var castId = intent.getLongExtra("cast_id",0)

        appViewModel.getCastDetail(castId) // call to obseve livedata state

        appViewModel.castDetailViewState.observe(this, Observer {
            render(it)
        })

        movieAdapter = PopularMoviesAdapter(this,this)

        cast_known_movies_rv.adapter = movieAdapter

    }

    private fun render(it: CastDetailViewState?) {
        when(it){
          is CastDetailViewState.CastDetailViewStateLoading -> showLoading()
          is CastDetailViewState.CastDetailViewStateFail -> showErrorMessage(it)
          is CastDetailViewState.CastDetailViewStateSuccess -> showDetail(it)
        }
    }

    private fun showDetail(state : CastDetailViewState.CastDetailViewStateSuccess){
        dialog.hide()
        setupView(state.cast)
    }

    private fun showErrorMessage(state: CastDetailViewState.CastDetailViewStateFail){
        dialog.hide()
       toast(state.message)
    }

    private fun showLoading() {
        dialog.setMessage("Loading...")
        dialog.setCancelable(false)
        dialog.show()
    }

}
