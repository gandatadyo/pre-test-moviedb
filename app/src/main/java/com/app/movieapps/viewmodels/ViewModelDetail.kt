package com.app.movieapps.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapps.R
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.data.DataReviewEntity
import com.app.movieapps.data.remote.ApiConfig
import com.app.movieapps.data.remote.response.ResponseDetailMovie
import com.app.movieapps.data.remote.response.ResponseReviewMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelDetail:ViewModel() {
    var itemMovie = MutableLiveData<DataMovieEntity>()
    var listReview: MutableLiveData<List<DataReviewEntity>> = MutableLiveData(listOf())

    fun getDataDetail(ctx:Context,idMovie:String){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getMovieDetail(idMovie,apiKey).enqueue(object : Callback<ResponseDetailMovie> {
            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        val item = DataMovieEntity(
                            responseBody.id.toString(),
                            responseBody.title,
                            responseBody.releaseDate,
                            responseBody.overview,
                            responseBody.posterPath,
                        )
                        itemMovie.value = item
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
                        val listTemp = listReview.value!!.toMutableList()
                        for (i in list!!.indices!!) {
                            val item = DataReviewEntity(
                                list[i]!!.id.toString(),
                                list[i]!!.updatedAt.toString(),
                                list[i]!!.author.toString(),
                                list[i]!!.createdAt.toString(),
                                list[i]!!.content.toString(),
                                list[i]!!.url.toString(),
                            )
                            listTemp.add(item)
                        }
                        listReview.value = listTemp
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