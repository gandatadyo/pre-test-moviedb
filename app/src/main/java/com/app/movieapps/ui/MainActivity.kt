package com.app.movieapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapps.data.source.entity.ItemMovieEntity
import com.app.movieapps.databinding.ActivityMainBinding
import com.app.movieapps.viewmodels.ViewModelMovie

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listData = arrayListOf<ItemMovieEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model: ViewModelMovie by viewModels()
        model.listMovie.observe(this, Observer<ArrayList<ItemMovieEntity>>{ users ->
            // update UI
            listData = users
        })

//        val itemAdapter = MovieAdapter { itemId: String, mode: String ->showDetail(itemId,mode) }
        with(binding.rvData) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
//            adapter = itemAdapter
        }

    }
}