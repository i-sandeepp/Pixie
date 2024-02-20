package com.sandeep.pixie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sandeep.pixie.R
import com.sandeep.pixie.interfaces.RowSelectListener
import com.sandeep.pixie.model.PixieItem
import com.sandeep.pixie.utils.DEMO_DESCRIPTION
import java.net.URL

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
class MainAdapter(
    private val context: Context,
    private var items: ArrayList<PixieItem>,
    private val listener: RowSelectListener):
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(
            items[position].author,
            DEMO_DESCRIPTION,
            items[position].download_url)

        holder.card.setOnClickListener {
            listener.onRowSelected(
                items[position].id,
                items[position].download_url,
                DEMO_DESCRIPTION
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(data: ArrayList<PixieItem>) {
        this.items = data
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.main_title)
        private val desc: TextView = itemView.findViewById(R.id.main_desc)
        private val image: ImageView = itemView.findViewById(R.id.main_image)
        val card: CardView = itemView.findViewById(R.id.main_card)

        fun bindData(title: String, desc: String, url: String) {
            this.title.text = title
            this.desc.text = desc

            Glide.with(itemView.context)
                .load(url)
                .into(image)
        }
    }
}