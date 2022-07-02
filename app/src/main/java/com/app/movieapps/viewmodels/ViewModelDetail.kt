package com.app.movieapps.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapps.R
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.data.remote.ApiConfig
import com.app.movieapps.data.remote.response.DataDetailMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelDetail:ViewModel() {
    var itemMovie = MutableLiveData<DataMovieEntity>()

    fun getDataDetail(ctx:Context,idMovie:String){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getMovieDetail(idMovie,apiKey).enqueue(object : Callback<DataDetailMovie> {
            override fun onResponse(call: Call<DataDetailMovie>, response: Response<DataDetailMovie>) {
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
            override fun onFailure(call: Call<DataDetailMovie>, t: Throwable) {
                Toast.makeText(ctx,t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}