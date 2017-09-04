package com.orientalfinance.eastcloud.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item.view.*

/**
 * Created by 29435 on 2017/8/17.
 */

class Adapter(var data: List<String>, val itemclick: (position: Int) -> Unit) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder? {
        val MyviewHolder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, null))
        MyviewHolder.itemView.button.setOnClickListener { view: View? ->
            itemclick(MyviewHolder.adapterPosition)

        }
        return MyviewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.button.text = data.get(position)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
