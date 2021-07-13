package com.app.movieapp.delegates

import com.app.movieapp.models.Cast

interface ClickCastDetail {
    fun onTapCast(cast : Cast?)
}