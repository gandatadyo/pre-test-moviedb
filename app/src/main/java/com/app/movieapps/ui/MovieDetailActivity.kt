package com.app.movieapps.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapps.R
import com.app.movieapps.adapters.AdapterReviewList
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.databinding.ActivityMovieDetailBinding
import com.app.movieapps.viewmodels.ViewModelDetail
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val model: ViewModelDetail by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        val adapterReviewList = AdapterReviewList()
        model.listReview.observe(this) { movies -> adapterReviewList.submitList(movies) }
        with (binding.rvReview){
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MovieDetailActivity)
            adapter = adapterReviewList
        }

        model.itemMovie.observe(this) { movie ->
            binding.lblOverview.text = movie.overview
            binding.lblReleseDate.text = movie.overview
            binding.lblOverview.text = movie.overview
            Glide
                .with(this@MovieDetailActivity)
                .load(this@MovieDetailActivity.getString(R.string.baseurl_img)+movie.poster_path)
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(binding.imgMovie);
        }

        val item = intent.getParcelableExtra<DataMovieEntity>("data")
        if(item!=null){
            model.getDataDetail(this, item.id.toString())
            model.getDataReview(this, item.id.toString())
        }


//        val youTubeView = findViewById<View>(R.id.youtube_view) as YouTubePlayerView
//        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this)

//        YouTubePlayerFragment
//        YouTubePlayer.Provider({data:String,})
//        val intent = YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.youtube_key), "8_NXifXOMCE")
//        startActivity(intent)

    }

}