package com.app.movieapps.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapps.R
import com.app.movieapps.adapters.AdapterReviewList
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.data.DataReviewEntity
import com.app.movieapps.data.remote.ApiConfig
import com.app.movieapps.data.remote.response.ResponseDetailMovie
import com.app.movieapps.data.remote.response.ResponseReviewMovie
import com.app.movieapps.data.remote.response.ResponseVideos
import com.app.movieapps.databinding.ActivityMovieDetailBinding
import com.app.movieapps.utils.YouTubeFailureRecoveryActivity
import com.bumptech.glide.Glide
import com.google.android.youtube.player.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : YouTubeFailureRecoveryActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    private var idYoutube = ""
    private val listReview = arrayListOf<DataReviewEntity?>()
    private val adapterReviewList = AdapterReviewList(listReview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener { finish() }

        binding.rvReview.setHasFixedSize(true)
        binding.rvReview.layoutManager = LinearLayoutManager(this@MovieDetailActivity)
        binding.rvReview.adapter = adapterReviewList

        val model = ViewModelCustom() // viewmodel custom because this class not suppport viewmodels
        val item = intent.getParcelableExtra<DataMovieEntity>("data")
        if(item!=null){
            model.getDataDetail(this, item.id.toString())
            model.getDataReview(this, item.id.toString())
            getDataVideos(this,item.id.toString())
        }
    }

    private fun getDataVideos(ctx: Context, idMovie:String){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getVideosUrl(idMovie,apiKey).enqueue(object : Callback<ResponseVideos> {
            override fun onResponse(call: Call<ResponseVideos>, response: Response<ResponseVideos>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        val list = responseBody.results
                        for (i in list!!.indices) {
                            if(list[i]!!.site.toString()=="YouTube"){
                                idYoutube = list[i]!!.key.toString()
                                break
                            }
                        }
                        initVideo()
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

    private fun initVideo() {
        val youTubePlayerFragment = fragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment
        youTubePlayerFragment.initialize(getString(R.string.youtube_key), this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer,wasRestored: Boolean) {
        if (!wasRestored) {
            player.cueVideo(idYoutube)
        }
    }

    override fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        return fragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment
    }

    inner class ViewModelCustom {
        fun getDataDetail(ctx:Context,idMovie:String){
            val apiKey = ctx.getString(R.string.moviedb_key)
            ApiConfig.getApiService().getMovieDetail(idMovie,apiKey).enqueue(object : Callback<ResponseDetailMovie> {
                override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null) {
                            binding.lblTItle.text = responseBody.title
                            binding.lblReleseDate.text = "Relese Date : "+responseBody.releaseDate
                            binding.lblOverview.text = responseBody.overview
                            Glide
                                .with(this@MovieDetailActivity)
                                .load(this@MovieDetailActivity.getString(R.string.baseurl_img)+responseBody.posterPath)
                                .centerCrop()
                                .placeholder(R.drawable.loading_spinner)
                                .into(binding.imgMovie)
                        }
                    } else {
                        Toast.makeText(ctx,"onFailure: ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                    Toast.makeText(ctx,t.message.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }

        fun getDataReview(ctx:Context,idMovie:String){
            val apiKey = ctx.getString(R.string.moviedb_key)
            ApiConfig.getApiService().getReviews(idMovie,apiKey).enqueue(object : Callback<ResponseReviewMovie> {
                override fun onResponse(call: Call<ResponseReviewMovie>, response: Response<ResponseReviewMovie>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null) {
                            val list = responseBody.results
                            for (i in list!!.indices) {
                                listReview.add(
                                    DataReviewEntity(
                                        list[i]!!.authorDetails!!.name.toString(),
                                        list[i]!!.authorDetails!!.username.toString(),
                                        list[i]!!.authorDetails!!.avatarPath.toString(),
                                        list[i]!!.authorDetails!!.rating.toString()
                                    )
                                )
                            }
                            adapterReviewList.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(ctx,"onFailure: ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ResponseReviewMovie>, t: Throwable) {
                    Toast.makeText(ctx,t.message.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
