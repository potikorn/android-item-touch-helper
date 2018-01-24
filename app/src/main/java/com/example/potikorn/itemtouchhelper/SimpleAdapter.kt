package com.example.potikorn.itemtouchhelper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.potikorn.itemtouchhelper.itemtouchhelper.CustomItemTouchHelperListener
import kotlinx.android.synthetic.main.simple_item.view.*
import java.util.*
import kotlin.properties.Delegates

class SimpleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), CustomItemTouchHelperListener {

    var items: MutableList<String> by Delegates.observable(mutableListOf()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.simple_item, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) =
            (holder as SimpleAdapter.SimpleViewHolder).onBindData(items[position])

    override fun getItemCount(): Int = items.size

    inner class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBindData(item: String) {
            itemView.txtOne.text = item
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}