package com.example.myapplication.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.model.Data


class DataAdapter(context: Context, listRecyclerItem: List<Data>) :
    RecyclerView.Adapter<ViewHolder>() {
    private val context: Context
    private val listRecyclerItem: List<Data>

    inner class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        val name: TextView
        init {
            name = itemView.findViewById(R.id.name)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView: View = LayoutInflater.from(context).inflate(
            R.layout.item_layout, parent, false
        )
        return ItemViewHolder(layoutView)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val itemViewHolder = viewHolder as ItemViewHolder
        val data: Data = listRecyclerItem[i] as Data
        itemViewHolder.name.text = data.firstName + " " + data.lastName
    }

    override fun getItemCount(): Int {
        return listRecyclerItem.size
    }

    companion object {
        private const val TYPE = 1
    }

    init {
        this.context = context
        this.listRecyclerItem = listRecyclerItem
    }
}