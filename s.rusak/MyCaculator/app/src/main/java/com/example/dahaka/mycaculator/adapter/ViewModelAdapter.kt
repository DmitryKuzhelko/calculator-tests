package com.example.dahaka.mycaculator.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dahaka.mycaculator.R
import com.example.dahaka.mycaculator.model.History
import kotlinx.android.synthetic.main.rv_item.view.*

class ViewModelAdapter(
        context: Context, private val itemClickListener: ViewModelAdapter.ItemClickListener,
        history: List<History> = emptyList()) : RecyclerView.Adapter<ViewModelAdapter.ViewHolder>() {

    private val history: MutableList<History> = ArrayList(history)

    fun refreshHistoryList(list: List<History>?) {
        history.clear()
        if (list != null) {
            history.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item, parent, false), itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(history[position])
    }

    override fun getItemCount() = history.size

    class ViewHolder(view: View, itemClickListener: ViewModelAdapter.ItemClickListener
    ) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClicked(this)
            }
        }

        fun bindItems(history: History) {
            itemView.timestamp.text = DateUtils.getRelativeTimeSpanString(history.timestamp)
            itemView.expression.text = history.expression
        }
    }

    interface ItemClickListener {
        fun onItemClicked(viewHolder: ViewHolder)
    }
}