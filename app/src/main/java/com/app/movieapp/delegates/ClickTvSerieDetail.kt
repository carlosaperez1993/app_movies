package com.app.movieapp.delegates

import com.app.movieapp.models.TvSerie

interface ClickTvSerieDetail {
    fun onTap(tvSerie: TvSerie?)
}