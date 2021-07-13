package com.app.movieapp.data.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.viewState.CastDetailViewState
import com.app.movieapp.viewState.DetailViewState
import com.app.movieapp.viewState.MainViewState
import com.app.movieapp.data.repository.MoviesRepository
import com.app.movieapp.models.FavMovie
import com.app.movieapp.models.Movie
import com.app.movieapp.viewState.FavoriteMoviesViewState
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class AppViewModel (private val moviesRepository : MoviesRepository) : ViewModel(){

    var viewState : MutableLiveData<MainViewState> = MutableLiveData()

    var detailViewState : MutableLiveData<DetailViewState> = MutableLiveData()

    var castDetailViewState : MutableLiveData<CastDetailViewState> =  MutableLiveData()

    var favoriteMoviesViewState : MutableLiveData<FavoriteMoviesViewState> = MutableLiveData()

    protected val job = SupervisorJob()
    protected val main: CoroutineDispatcher = Dispatchers.Main
    protected val io: CoroutineDispatcher = Dispatchers.IO


    init {
        mainScreenData()
    }

     @SuppressLint("CheckResult")
     fun mainScreenData(page : Int?=1){
        var list : List<Movie> = emptyList()
        moviesRepository.getPopularMovies(page)
            .flatMap {
                list = it.results
                moviesRepository.getPopularTvSeries(page)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { viewState.postValue(MainViewState.PopularMoviesLoadingState)  }
            .doOnError { viewState.postValue(MainViewState.PopularMovieFailState(it.localizedMessage)) }
            .subscribe { viewState.postValue(MainViewState.PopularMovieSuccessState(list, it.results)) }
    }

    @SuppressLint("CheckResult")
    fun getMovieDetail(id : Long) {
        moviesRepository.getMovieDetail(id)
            .doOnSubscribe { t ->detailViewState.postValue(DetailViewState.MovieDetailViewStateLoading) }
            .doOnError { t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateFail(t.localizedMessage)) }
            .subscribe{ t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateSuccess(t))  }
    }
    @SuppressLint("CheckResult")
    fun getTvSerieDetail(id : Long) {
        moviesRepository.getTvSerieDetail(id)
            .doOnSubscribe { t ->detailViewState.postValue(DetailViewState.MovieDetailViewStateLoading) }
            .doOnError { t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateFail(t.localizedMessage)) }
            .subscribe{ t -> detailViewState.postValue(DetailViewState.TvSerieDetailViewStateSuccess(t))  }
    }

    @SuppressLint("CheckResult")
    fun getCastDetail(id : Long){
        moviesRepository.getCastDetail(id)
            .doOnSubscribe{ t -> castDetailViewState.postValue(CastDetailViewState.CastDetailViewStateLoading) }
            .doOnError { t -> castDetailViewState.postValue(CastDetailViewState.CastDetailViewStateFail(t.localizedMessage)) }
            .subscribe { t -> castDetailViewState.postValue(CastDetailViewState.CastDetailViewStateSuccess(t)) }
    }

    @SuppressLint("CheckResult")
    fun getFavorite(){
        moviesRepository.getFavorites()
            .doOnSubscribe{ t -> favoriteMoviesViewState.postValue(FavoriteMoviesViewState.FavoriteMovieViewStateLoading) }
            .doOnError { t -> favoriteMoviesViewState.postValue(FavoriteMoviesViewState.FavoriteMovieViewStateFail(t.localizedMessage)) }
            .subscribe { t -> favoriteMoviesViewState.postValue(FavoriteMoviesViewState.FavoriteMovieViewStateSuccess(t)) }
    }
    @SuppressLint("CheckResult")
    fun saveFavorite(favMovie: FavMovie) =  GlobalScope.launch(io + job) {
        moviesRepository.saveFavorite(favMovie)
    }
    @SuppressLint("CheckResult")
    fun existAsFavorite(id: Long){
        moviesRepository.existAsFavorite(id)
            .doOnSubscribe{ t -> favoriteMoviesViewState.postValue(FavoriteMoviesViewState.FavoriteMovieViewStateLoading) }
            .doOnError { t -> favoriteMoviesViewState.postValue(FavoriteMoviesViewState.FavoriteMovieViewStateFail(t.localizedMessage)) }
            .subscribe { t -> favoriteMoviesViewState.postValue(FavoriteMoviesViewState.FavoriteMovieExistViewStateSuccess(t)) }
    }
    @SuppressLint("CheckResult")
    fun deleteFavorite(id:Long ) =  GlobalScope.launch(io + job) {
        moviesRepository.deleteFavorite(id)
    }

    override fun onCleared() {
        super.onCleared()
        viewState.value = null
        detailViewState.value = null
    }

}