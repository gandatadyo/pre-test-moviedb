package com.app.movieapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.movieapps.databinding.ActivityMainBinding
import com.app.movieapps.databinding.ActivityMovieDetailBinding

class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}