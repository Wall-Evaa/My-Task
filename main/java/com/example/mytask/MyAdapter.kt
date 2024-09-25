package com.example.mytask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var con: Context, var list: List<Result>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
       var img = v.findViewById<ShapeableImageView>(R.id.headingImage)
        var tvTitle = v.findViewById<TextView>(R.id.headingTitle)
        var tvDesc = v.findViewById<TextView>(R.id.headingdDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.fragment_home,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      Glide.with(con).load(list[position].Image).into(holder.img)
        holder.tvTitle.text = list[position].Title
        holder.tvDesc.text = list[position].Description
    }
}