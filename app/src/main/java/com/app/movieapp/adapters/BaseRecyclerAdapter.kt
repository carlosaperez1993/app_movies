package com.app.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.viewholders.BaseViewHolder


abstract class BaseRecyclerAdapter<T : BaseViewHolder<*>,W>(context: Context) : RecyclerView.Adapter<T>() {

    protected var layoutInflater : LayoutInflater

    protected var data : List<W>? = null

    init {
        layoutInflater = LayoutInflater.from(context)
        data = listOf()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun setNewData(newData : List<W>?){
        data = newData
        notifyDataSetChanged()
    }
}