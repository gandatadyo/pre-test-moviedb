package com.app.movieapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapps.R
import com.app.movieapps.data.DataReviewEntity
import com.app.movieapps.databinding.AdapterReviewBinding
import com.bumptech.glide.Glide

class AdapterReviewList(private val listData: ArrayList<DataReviewEntity?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeItem = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemAdapter = AdapterReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return CardViewHolder(itemAdapter)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == viewTypeItem) {
            val holderTemp :CardViewHolder = holder as CardViewHolder
            listData[position]?.let { holderTemp.bind(it) }
        }
    }

    inner class CardViewHolder(private val binding: AdapterReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataReviewEntity) {
            binding.lblTitle.text=data.username
            binding.lblDescription.text=data.name
            binding.lblRating.text="Rating ${data.rating}/10"
            Glide
                .with(itemView.context)
                .load(data.avatar_path)
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(binding.imgMovie);
        }
    }
}