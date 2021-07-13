package com.app.movieapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.app.movieapp.delegates.ClickCastDetail
import com.app.movieapp.R
import com.app.movieapp.viewholders.CastViewHolder
import com.app.movieapp.models.Cast

class CastAdapter(context: Context,clickCastDetail: ClickCastDetail) : BaseRecyclerAdapter<CastViewHolder,Cast>(context) {

    val clickCastDetail : ClickCastDetail = clickCastDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view : View = layoutInflater.inflate(R.layout.cast_item,parent,false)
        return CastViewHolder(view, clickCastDetail)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
       holder!!.bind(data!!.get(position))
    }
}