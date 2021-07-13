package com.app.movieapp.viewState

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewFlipper
/*
* Esta clase se utiliza para manejar que informacion estaremos viendo en la pantalla, una vez hagamos seleccionado algun tab
*
* */

class ViewFlipperState constructor(context: Context?, attrs: AttributeSet? = null) : ViewFlipper(context, attrs) {

    constructor(context: Context?) : this(context, null)

    companion object {
        const val MOVIES = 0
        const val SERIES = 1
        const val FAVORITES = 2
    }

    override fun onDetachedFromWindow() = try {
        super.onDetachedFromWindow()
    } catch (ex: Exception) {
        stopFlipping()
    }

    fun setMovies(){
        flipTo(MOVIES)
    }

    fun setTvSeries(){
        flipTo(SERIES)
    }

    fun setFavorites(){
        flipTo(FAVORITES)
    }

    private fun flipTo(position: Int){
        animateFirstView = true
        displayedChild = position
    }
}