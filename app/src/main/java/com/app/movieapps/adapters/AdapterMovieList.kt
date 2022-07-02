package com.app.movieapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapps.R
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.databinding.AdapterMovieBinding
import com.bumptech.glide.Glide

class AdapterMovieList(val clickListener:(DataMovieEntity)->Unit) : ListAdapter<DataMovieEntity, AdapterMovieList.MovieViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class MovieViewHolder(private val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataMovieEntity) {
            binding.lblTitle.text=item.original_title
//            binding.lblReleseDate.text=item.release_date
//            binding.lblOverview.text=item.overview
            binding.itemClick.setOnClickListener {
                clickListener(item)
            }

            Glide
                .with(itemView.context)
                .load(itemView.context.getString(R.string.baseurl_img)+item.poster_path)
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(binding.imgMovie);
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<DataMovieEntity>() {
        override fun areItemsTheSame(oldItem: DataMovieEntity, newItem: DataMovieEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataMovieEntity, newItem: DataMovieEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}