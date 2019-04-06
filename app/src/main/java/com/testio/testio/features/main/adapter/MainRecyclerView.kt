package com.testio.testio.features.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.testio.testio.R
import com.testio.testio.features.main.MainClickListener
import com.testio.testio.features.topics.adapter.Menu

class MainRecyclerView(private val values: List<Menu>) : RecyclerView.Adapter<MainViewHolder>() {

    var listener: MainClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_image, p0, false)
        return MainViewHolder(itemView, listener)
    }

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: MainViewHolder, pos: Int) {
        Picasso.get().load(values[pos].image).into(holder.image)
        holder.containItem?.setOnClickListener {
            listener?.onImageClicked(values[pos])
        }
    }
}