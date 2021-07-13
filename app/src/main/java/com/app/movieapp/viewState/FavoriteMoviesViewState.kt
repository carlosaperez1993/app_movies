package com.app.movieapp.viewState

import com.app.movieapp.models.FavMovie
/*
* Esta clase maneja los estados de Favoritos, para poder cargar y verificar los favoritos que se han guardado en la base de datos.*/
sealed class FavoriteMoviesViewState {
    object FavoriteMovieViewStateLoading : FavoriteMoviesViewState()

    data class FavoriteMovieViewStateSuccess(val list : List<FavMovie>) : FavoriteMoviesViewState()

    data class FavoriteMovieExistViewStateSuccess(val exist : Boolean) : FavoriteMoviesViewState()

    data class FavoriteMovieViewStateFail(val message : String) : FavoriteMoviesViewState()
}