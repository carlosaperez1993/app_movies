package com.app.movieapp.viewState

import com.app.movieapp.models.Movie
import com.app.movieapp.models.TvSerieDetail
/*
* Estadp de detalle de la aplicación en donde podemos cargar los datos del detalle de una serie o pelicula,
* se reutilizaron los estados de cargar y de fallido, cada vez que se haga un request,
* y tambien se manejan los diferentes estados, ya sea que que suceda algun error, o aun este esperando respuesta  */
sealed class DetailViewState {

    object MovieDetailViewStateLoading : DetailViewState()

    data class MovieDetailViewStateSuccess(val movie: Movie) : DetailViewState()

    data class TvSerieDetailViewStateSuccess(val tvSerieDetail: TvSerieDetail) : DetailViewState()

    data class MovieDetailViewStateFail(val message : String) : DetailViewState()

}