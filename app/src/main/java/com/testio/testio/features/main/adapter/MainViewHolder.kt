package com.testio.testio.features.main.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.testio.testio.R
import com.testio.testio.features.main.MainClickListener
import com.testio.testio.features.topics.adapter.Menu
import kotlinx.android.synthetic.main.item_image.view.*

class MainViewHolder(itemView: View, private val onClickItem: MainClickListener?) : RecyclerView.ViewHolder(itemView) {

    var image: ImageView? = null
    var containItem: ConstraintLayout? = null

    init {
        image = itemView.findViewById(R.id.recyclerImage)
        containItem = itemView.findViewById(R.id.mainConstraint)
    }

    fun bind(menu: Menu) {
        Picasso.get().load(R.drawable.learn).error(R.mipmap.error_sm).into(image)
        containItem?.setOnClickListener {
            onClickItem?.onImageClicked(menu)
        }
    }
}