package com.mad.campusconnect

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private val context: android.content.Context,
    private var items: List<Event>,
    private val newsList: List<NewsItem>,
    private val onEventClick: (Event) -> Unit,
    private val onSearchChanged: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_EVENT = 1
    }

    // --- Header ViewHolder ---
    inner class HeaderVH(view: View) : RecyclerView.ViewHolder(view) {
        val rvNews: RecyclerView = view.findViewById(R.id.rvNews)
        val etSearch: EditText = view.findViewById(R.id.etSearch)
    }

    // --- Event ViewHolder ---
    class EventVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvExclusiveTag: TextView = view.findViewById(R.id.tvExclusiveTag)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvTitle: TextView = view.findViewById(R.id.tvEventTitle)
        val tvDesc: TextView = view.findViewById(R.id.tvEventDesc)
        val tvDate: TextView = view.findViewById(R.id.tvEventDate)
        val tvTime: TextView = view.findViewById(R.id.tvEventTime)
        val llTags: LinearLayout = view.findViewById(R.id.llTags)
    }

    override fun getItemViewType(position: Int) =
        if (position == 0) TYPE_HEADER else TYPE_EVENT

    override fun getItemCount() = items.size + 1  // +1 for header

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.header_main, parent, false)
            HeaderVH(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
            EventVH(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderVH) {
            // Wire up news list
            holder.rvNews.layoutManager = LinearLayoutManager(context)
            holder.rvNews.adapter = NewsAdapter(newsList)

            // Wire up search
            holder.etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    onSearchChanged(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        } else if (holder is EventVH) {
            val event = items[position - 1]  // offset by 1 for header

            if (event.exclusiveTag.isNotEmpty()) {
                holder.tvExclusiveTag.text = event.exclusiveTag
                holder.tvExclusiveTag.visibility = View.VISIBLE
            } else {
                holder.tvExclusiveTag.visibility = View.GONE
            }

            holder.tvStatus.text = event.status
            holder.tvPrice.text = event.price
            holder.tvTitle.text = event.title
            holder.tvDesc.text = event.description
            holder.tvDate.text = event.date
            holder.tvTime.text = event.time

            // Tags
            holder.llTags.removeAllViews()
            for (tag in event.tags) {
                val tagView = TextView(context)
                tagView.text = tag
                tagView.textSize = 11f
                tagView.setTextColor(Color.parseColor("#2DD4BF"))
                tagView.setPadding(24, 8, 24, 8)
                val shape = GradientDrawable()
                shape.shape = GradientDrawable.RECTANGLE
                shape.cornerRadius = 40f
                shape.setColor(Color.parseColor("#0D2B2B"))
                shape.setStroke(2, Color.parseColor("#2DD4BF"))
                tagView.background = shape
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.marginEnd = 8
                tagView.layoutParams = params
                holder.llTags.addView(tagView)
            }

            holder.itemView.setOnClickListener { onEventClick(event) }
        }
    }

    fun updateList(newList: List<Event>) {
        val oldSize = items.size
        val newSize = newList.size
        items = newList

        if (newSize == oldSize) {
            notifyItemRangeChanged(1, newSize)
        } else if (newSize > oldSize) {
            notifyItemRangeChanged(1, oldSize)
            notifyItemRangeInserted(oldSize + 1, newSize - oldSize)
        } else {
            notifyItemRangeChanged(1, newSize)
            notifyItemRangeRemoved(newSize + 1, oldSize - newSize)
        }
    }
}