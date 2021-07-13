package com.app.movieapp.viewState

import com.app.movieapp.models.Movie
import com.app.movieapp.models.TvSerie
/*
* Estadp principal de la aplicación en donde podemos cargar los datos cada vez que se haga un request,
* y tambien se manejan los diferentes estados, ya sea que que suceda algun error, o aun este esperando respuesta  */
sealed class MainViewState {

    object PopularMoviesLoadingState : MainViewState()

    data class PopularMovieSuccessState(val list : List<Movie>, val listTvSeries : List<TvSerie>) : MainViewState()

    data class PopularMovieFailState(val message : String) : MainViewState()

}