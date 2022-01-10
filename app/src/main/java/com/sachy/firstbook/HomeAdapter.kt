package com.sachy.firstbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(val mCallback:AdapterCallback,val data:ArrayList<String>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var tvCharacter: TextView = view.findViewById(R.id.tvCharacter)
        var itemFrame:FrameLayout=view.findViewById(R.id.itemFrame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCharacter.text=data[position]
        holder.itemFrame.setOnClickListener{mCallback.itemClick(position)}
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface AdapterCallback{
        fun itemClick(position:Int)
    }
}