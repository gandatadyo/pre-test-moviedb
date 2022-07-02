package com.app.movieapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapps.adapters.AdapterMovieList
import com.app.movieapps.data.DataGenreEntity
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.databinding.ActivityMovieGenreBinding
import com.app.movieapps.viewmodels.ViewModelMovieByGenre

class MovieGenreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieGenreBinding
    private val model: ViewModelMovieByGenre by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        val adapterMovieByGenreList = AdapterMovieList { chooseDetailMovie(it) }
        model.listMovie.observe(this) { movies -> adapterMovieByGenreList.submitList(movies) }
        with (binding.rvData){
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MovieGenreActivity,androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterMovieByGenreList
        }

        val item = intent.getParcelableExtra<DataGenreEntity>("data")
        if(item!=null){
            model.getDataMovieByGenre(this,item.id.toString())
        }
    }

    private fun chooseDetailMovie(data: DataMovieEntity){
        val intent = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }
}