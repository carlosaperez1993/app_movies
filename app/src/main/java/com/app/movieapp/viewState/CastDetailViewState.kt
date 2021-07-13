package com.app.movieapp.viewState

import com.app.movieapp.models.Cast
/*
* Esta clase maneja es estado de los Cast de las peliculas.
* */
sealed class CastDetailViewState {

    object CastDetailViewStateLoading : CastDetailViewState()

    data class CastDetailViewStateSuccess(val cast: Cast) : CastDetailViewState()

    data class CastDetailViewStateFail(val message : String) : CastDetailViewState()

}
