package com.app.movieapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.app.movieapp.delegates.ClickCastDetail
import com.app.movieapp.R
import com.app.movieapp.models.Cast
import kotlinx.android.synthetic.main.cast_item.view.*

class CastViewHolder(itemView: View,castDetail: ClickCastDetail) : BaseViewHolder<Cast>(itemView),View.OnClickListener {

    var cast : Cast? = null

    var clickCastDetail : ClickCastDetail = castDetail

    override fun bind(data: Cast) {
        this.cast = data

        itemView.cast_character_name.text = data.character

        itemView.episodes.text = data.name

        Glide.with(itemView.context)
            .load("http://image.tmdb.org/t/p/w300"+data.profilePath)
            .placeholder(R.drawable.digimon)
            .into(itemView.cast_poster)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        clickCastDetail.onTapCast(cast)
    }
}