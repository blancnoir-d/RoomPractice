package com.project.shis.roompractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.shis.roompractice.room.Cat

class CatListAdapter(val context: Context, var cats:List<Cat> ): RecyclerView.Adapter<CatListAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cat_list,parent,false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(cats[position])
    }



    inner class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val tvCatName = itemView.findViewById<TextView>(R.id.tvCatName)
        val tvLifeSpan = itemView.findViewById<TextView>(R.id.tvLifeSpan)
        val tvOrigin = itemView.findViewById<TextView>(R.id.tvOrigin)

        fun bind(cat: Cat){
            tvCatName.text = cat.catName
            tvLifeSpan.text = cat.lifeSpan.toString()
            tvOrigin.text = cat.origin
        }
    }

    fun setList(update:List<Cat>){
        cats = update
        notifyDataSetChanged()
    }
}