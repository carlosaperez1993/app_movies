package com.app.movieapp.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.R
import com.app.movieapp.adapters.FavoritesAdapter
import com.app.movieapp.adapters.PopularMoviesAdapter
import com.app.movieapp.adapters.TvSeriesAdapter
import com.app.movieapp.data.viewmodels.AppViewModel
import com.app.movieapp.delegates.ClickCastDetail
import com.app.movieapp.delegates.ClickMovieDetail
import com.app.movieapp.delegates.ClickTvSerieDetail
import com.app.movieapp.models.Cast
import com.app.movieapp.models.Movie
import com.app.movieapp.models.TvSerie
import com.app.movieapp.utils.toast
import com.app.movieapp.viewState.FavoriteMoviesViewState
import com.app.movieapp.viewState.MainViewState
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity: AppCompatActivity(), ClickMovieDetail,ClickCastDetail,ClickTvSerieDetail {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var isLoading: Boolean = false
    var viewState : MutableLiveData<MainViewState> = MutableLiveData()
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var layoutManagerTv : LinearLayoutManager
    private var movies = mutableListOf<Movie>()
    private var series = mutableListOf<TvSerie>()
    private var page :Int = 1

    override fun onTapCast(cast: Cast?) {
        val intent  = Intent(
            this,
            ActorDetailActivity::class.java
        )
        intent.putExtra("cast_id", cast?.castId)
        startActivity(intent)
    }

    override fun onTap(movie: Movie?) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movie?.movieId)
        startActivity(intent)
    }

    lateinit var appViewModel : AppViewModel

    lateinit var popularMoviesAdapter: PopularMoviesAdapter

    lateinit var tvSeriesAdapter: TvSeriesAdapter

    lateinit var favoritesAdapter: FavoritesAdapter

    private lateinit var progressDialog : ProgressDialog

    override fun onPause() {
        super.onPause()
        progressDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this@MainActivity)

        appViewModel = getViewModel<AppViewModel>()

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.movies -> {
                    flipper.setMovies()
                    true
                }
                R.id.series -> {
                    flipper.setTvSeries()
                    true
                }
                R.id.favorites -> {
                   flipper.setFavorites()
                    this.appViewModel
                    true
                }
                else -> false
            }
        }
        popularMoviesAdapter = PopularMoviesAdapter(this, this)

        tvSeriesAdapter = TvSeriesAdapter(this, this)

        favoritesAdapter = FavoritesAdapter(this,this)

        appViewModel.viewState.observe(this, Observer {
            render(it)
        })

        popular_movies_rv.setHasFixedSize(true)

        popular_movies_rv.adapter = popularMoviesAdapter
        layoutManager = LinearLayoutManager(this)
        popular_movies_rv.layoutManager = layoutManager
        popular_movies_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == movies.size - 1) {
                        page++
                        isLoading = true
                        loadData(page)
                    }
                }
            }
        })

        tvSeries_rv.setHasFixedSize(true)

        tvSeries_rv.adapter = tvSeriesAdapter
        layoutManagerTv = LinearLayoutManager(this)
        tvSeries_rv.layoutManager = layoutManagerTv
        tvSeries_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading) {
                    if (layoutManagerTv.findLastCompletelyVisibleItemPosition() == series.size - 1) {
                        page++
                        isLoading = true
                        loadData(page)
                    }
                }
            }
        })
        fav_movies_rv.setHasFixedSize(true)

        fav_movies_rv.adapter = favoritesAdapter

        appViewModel.getFavorite()

        appViewModel.favoriteMoviesViewState.observe(this, Observer {
            renderFav(it)
        })
    }

    private fun renderFav(it: FavoriteMoviesViewState) {
        when (it) {
            is FavoriteMoviesViewState.FavoriteMovieViewStateLoading -> renderPopularMovieLoadingState()
            is FavoriteMoviesViewState.FavoriteMovieViewStateSuccess -> renderFavMovieSuccessState(it)
            is FavoriteMoviesViewState.FavoriteMovieViewStateFail -> renderFavMovieFailState(it)
        }
    }
    private fun renderFavMovieSuccessState(state: FavoriteMoviesViewState.FavoriteMovieViewStateSuccess) {
        progressDialog.hide()
        favoritesAdapter.setNewData(state.list)
    }

    private fun renderFavMovieFailState(state: FavoriteMoviesViewState.FavoriteMovieViewStateFail) {
        progressDialog.hide()
        toast(state.message)
    }

    private fun loadData(page: Int? =1) {
        this.appViewModel.mainScreenData(page)
    }

    private fun render(state: MainViewState) {
        when (state) {
            is MainViewState.PopularMoviesLoadingState -> renderPopularMovieLoadingState()
            is MainViewState.PopularMovieSuccessState -> renderPopularMovieSuccessState(state)
            is MainViewState.PopularMovieFailState -> renderPopularMovieFailState(state)
        }
    }


    private fun renderPopularMovieLoadingState() {
        showLoading()
    }

    private fun renderPopularMovieSuccessState(state: MainViewState.PopularMovieSuccessState) {
        progressDialog.hide()
        isLoading = false
        movies.addAll(state.list)
        series.addAll(state.listTvSeries)
        popularMoviesAdapter.setNewData(movies)
        tvSeriesAdapter.setNewData(series)
    }
    
    private fun renderPopularMovieFailState(state: MainViewState.PopularMovieFailState) {
        progressDialog.hide()
        toast(state.message)
    }

    private fun showLoading(){
            progressDialog.setMessage("Loading...")
            progressDialog.setCancelable(false)
            progressDialog.show()
    }

    override fun onTap(tvSerie: TvSerie?) {
        val intent = Intent(this, TvSerieDetailActivity::class.java)
        intent.putExtra("serie_id", tvSerie?.id)
        startActivity(intent)
    }

}
