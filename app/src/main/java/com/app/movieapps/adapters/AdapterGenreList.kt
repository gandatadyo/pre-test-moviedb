package com.app.movieapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapps.data.DataGenreEntity
import com.app.movieapps.databinding.AdapterGenreBinding

class AdapterGenreList(val clickListener:(DataGenreEntity)->Unit) : ListAdapter<DataGenreEntity, AdapterGenreList.MovieViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = AdapterGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class MovieViewHolder(private val binding: AdapterGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataGenreEntity?) {
            if (item != null) {
                binding.btnGenre.text =item.name
                binding.btnGenre.setOnClickListener {
                    clickListener(item)
                }
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<DataGenreEntity>() {
        override fun areItemsTheSame(oldItem: DataGenreEntity, newItem: DataGenreEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataGenreEntity, newItem: DataGenreEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}