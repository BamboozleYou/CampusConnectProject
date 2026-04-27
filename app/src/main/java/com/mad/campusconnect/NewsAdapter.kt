package com.mad.campusconnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val items: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsVH>() {

    class NewsVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvNewsTitle)
        val tvDate: TextView = view.findViewById(R.id.tvNewsDate)
        val tvBody: TextView = view.findViewById(R.id.tvNewsBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsVH(view)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvDate.text = item.date
        holder.tvBody.text = item.body
    }

    override fun getItemCount() = items.size
}