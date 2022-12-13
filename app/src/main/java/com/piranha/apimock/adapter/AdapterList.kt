package com.piranha.apimock.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piranha.apimock.ListData
import com.piranha.apimock.R

class AdapterList (private var ListData:ArrayList<ListData> ) :
RecyclerView.Adapter<AdapterList.viewHolder>(){
    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val content: TextView = itemView.findViewById(R.id.tv_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = ListData[position]
        Glide.with(holder.itemView)

        holder.title.text = data.title
        holder.content.text = data.content
    }

    override fun getItemCount()= ListData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ListData>){
        ListData.clear()
        ListData.addAll(data)
        notifyDataSetChanged()
    }

}