package com.app.movieapps.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapps.R
import com.app.movieapps.data.DataGenreEntity
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.data.remote.ApiConfig
import com.app.movieapps.data.remote.response.ResponseGenre
import com.app.movieapps.data.remote.response.ResponseListMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMain:ViewModel() {
    var indexPagging : MutableLiveData<Int> = MutableLiveData( 1 )
    var listGenre: MutableLiveData<List<DataGenreEntity>> = MutableLiveData(listOf())
    var listMovie: MutableLiveData<List<DataMovieEntity>> = MutableLiveData(listOf())

    fun getDataGenre(ctx:Context){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getGenreMovieList(apiKey).enqueue(object : Callback<ResponseGenre> {
            override fun onResponse(call: Call<ResponseGenre>,response: Response<ResponseGenre>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        val list = responseBody.genres
                        val listTemp = listGenre.value!!.toMutableList()
                        for (i in list!!.indices!!) {
                            val item = DataGenreEntity(
                                list[i]!!.id.toString(),
                                list[i]!!.name.toString(),
                            )
                            listTemp.add(item)
                        }
                        listGenre.value = listTemp
                    }
                    Toast.makeText(ctx,"finish",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(ctx,"onFailure: ${response.message()}",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ResponseGenre>, t: Throwable) {
                Toast.makeText(ctx,t.message.toString(),Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getDataMovie(ctx:Context){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getMovieList(apiKey).enqueue(object : Callback<ResponseListMovie> {
            override fun onResponse(call: Call<ResponseListMovie>,response: Response<ResponseListMovie>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        val list = responseBody.results
                        val listTemp = listMovie.value!!.toMutableList()
                        for (i in list!!.indices!!) {
                            val item = DataMovieEntity(
                                list[i]!!.id.toString(),
                                list[i]!!.title.toString(),
                                list[i]!!.releaseDate.toString(),
                                list[i]!!.overview.toString(),
                                list[i]!!.posterPath.toString(),
                            )
                            listTemp.add(item)
                        }
                        listMovie.value = listTemp
                    }
                    Toast.makeText(ctx,"finish",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(ctx,"onFailure: ${response.message()}",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Toast.makeText(ctx,t.message.toString(),Toast.LENGTH_LONG).show()
            }
        })
    }
}