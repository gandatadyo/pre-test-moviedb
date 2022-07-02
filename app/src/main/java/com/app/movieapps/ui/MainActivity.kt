package com.app.movieapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapps.adapters.AdapterGenreList
import com.app.movieapps.adapters.AdapterMovieList
import com.app.movieapps.data.DataGenreEntity
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.databinding.ActivityMainBinding
import com.app.movieapps.viewmodels.ViewModelMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: ViewModelMain by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        val adapterGenreList = AdapterGenreList { chooseMovieByGenre(it) }
        binding.rvGenre.setHasFixedSize(true)
        binding.rvGenre.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        binding.rvGenre.adapter = adapterGenreList

        val adapterMovieList = AdapterMovieList { chooseDetailMovie(it) }
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = GridLayoutManager(this@MainActivity, 3)
        binding.rvData.adapter = adapterMovieList

        model.listGenre.observe(this) { genre ->
            adapterGenreList.submitList(genre)
        }
        model.listMovie.observe(this) { movies ->
            adapterMovieList.submitList(movies)
        }

        model.getDataGenre(this)
        model.getDataMovie(this)
    }

    private fun chooseDetailMovie(data:DataMovieEntity){
        val intent = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }

    private fun chooseMovieByGenre(data:DataGenreEntity){
        val intent = Intent(this,MovieGenreActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }
}