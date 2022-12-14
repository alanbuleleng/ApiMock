package com.piranha.apimock.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piranha.apimock.ListData
import com.piranha.apimock.R
import com.piranha.apimock.model.ResponseDelete
import com.piranha.apimock.network.ApiServis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterList (private var ListData:ArrayList<ListData> ) :
RecyclerView.Adapter<AdapterList.viewHolder>(){
    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val content: TextView = itemView.findViewById(R.id.tv_content)
        val delete: ImageView = itemView.findViewById(R.id.img_delete)
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
        holder.delete.setOnClickListener {
            ApiServis.endNetwork.DeleteTODO("${data.id}").enqueue(object :
                Callback<ResponseDelete>{
                override fun onResponse(
                    call: Call<ResponseDelete>,
                    response: Response<ResponseDelete>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(
                            holder.itemView.context, "Delete TODO Succes",
                            Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        Toast.makeText(holder.itemView.context, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDelete>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "$t", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun getItemCount()= ListData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ListData>){
        ListData.clear()
        ListData.addAll(data)
        notifyDataSetChanged()
    }

}