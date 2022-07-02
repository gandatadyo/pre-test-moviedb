package com.app.movieapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapps.data.DataReviewEntity
import com.app.movieapps.databinding.AdapterReviewBinding

class AdapterReviewList : ListAdapter<DataReviewEntity, AdapterReviewList.MovieViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = AdapterReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class MovieViewHolder(private val binding: AdapterReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataReviewEntity?) {
            if (item != null) {
                binding.lblTitle.text=item.author
                binding.lblDescription.text=item.content

//                Glide
//                    .with(itemView.context)
//                    .load(itemView.context.getString(R.string.baseurl_img)+item.poster_path)
//                    .centerCrop()
//                    .placeholder(R.drawable.loading_spinner)
//                    .into(binding.imgMovie);

            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<DataReviewEntity>() {
        override fun areItemsTheSame(oldItem: DataReviewEntity, newItem: DataReviewEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataReviewEntity, newItem: DataReviewEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}