package com.app.movieapp.delegates

import com.app.movieapp.models.Movie

interface ClickMovieDetail {
    fun onTap(movie: Movie?)
}