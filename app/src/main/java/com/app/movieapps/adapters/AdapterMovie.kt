package com.app.movieapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapps.R
import com.app.movieapps.data.source.entity.ItemMovieEntity
import com.app.movieapps.databinding.AdapterMovieBinding
import com.bumptech.glide.Glide

class AdapterMovie(private val listData: ArrayList<ItemMovieEntity?>, val clickLister: (ItemMovieEntity) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeItem = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemAdapter = AdapterMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return CardViewHolder(itemAdapter)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == viewTypeItem) {
            val holderTemp :CardViewHolder = holder as CardViewHolder
            listData[position]?.let { holderTemp.bind(it) }
        }
    }

    inner class CardViewHolder(private val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemMovieEntity) {
            with(binding){
                lblTitle.text = data.title
                lblReleseDate.text = data.dateItem
                lblOverview.text = data.description
                Glide.with(itemView.context)
                    .load(data.imagePath)
                    .error(ContextCompat.getDrawable(itemView.context, R.drawable.blank_image))
                    .into(binding.imgMovie)
//                cardClik.setOnClickListener {
//                    clickLister(data)
//                }
            }
        }
    }
}