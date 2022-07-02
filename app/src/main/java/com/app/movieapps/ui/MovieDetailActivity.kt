package com.app.movieapps.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapps.R
import com.app.movieapps.adapters.AdapterReviewList
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.data.DataReviewEntity
import com.app.movieapps.data.remote.ApiConfig
import com.app.movieapps.data.remote.response.ResponseReviewMovie
import com.app.movieapps.data.remote.response.ResponseVideos
import com.app.movieapps.databinding.ActivityMovieDetailBinding
import com.app.movieapps.viewmodels.ViewModelDetail
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val model: ViewModelDetail by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        binding.imgBack.setOnClickListener { finish() }

        val adapterReviewList = AdapterReviewList()
        model.listReview.observe(this) { movies -> adapterReviewList.submitList(movies) }
        with (binding.rvReview){
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MovieDetailActivity)
            adapter = adapterReviewList
        }

        model.itemMovie.observe(this) { movie ->
            binding.lblTItle.text = movie.original_title
            binding.lblReleseDate.text = "Relese Date : "+movie.release_date
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
            getDataVideos(this,item.id.toString())
        }


//        val youTubeView = findViewById<View>(R.id.youtube_view) as YouTubePlayerView
//        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this)

//        YouTubePlayerFragment
//        YouTubePlayer.Provider({data:String,})
//        val intent = YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.youtube_key), "8_NXifXOMCE")
//        startActivity(intent)

    }

    private fun getDataVideos(ctx: Context, idMovie:String){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getVideosUrl(idMovie,apiKey).enqueue(object : Callback<ResponseVideos> {
            override fun onResponse(call: Call<ResponseVideos>, response: Response<ResponseVideos>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        val list = responseBody.results
                        var idYoutube = ""
                        for (i in list!!.indices!!) {
                            if(list[i]!!.site.toString()=="YouTube"){
                                idYoutube = list[i]!!.key.toString()
                                break
                            }
                        }
                        Toast.makeText(ctx,"ID Youtube: $idYoutube", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(ctx,"onFailure: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ResponseVideos>, t: Throwable) {
                Toast.makeText(ctx,t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}