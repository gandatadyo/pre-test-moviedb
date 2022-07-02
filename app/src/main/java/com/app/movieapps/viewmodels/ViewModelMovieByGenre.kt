package com.app.movieapps.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapps.R
import com.app.movieapps.data.DataMovieEntity
import com.app.movieapps.data.remote.ApiConfig
import com.app.movieapps.data.remote.response.ResponseListMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMovieByGenre:ViewModel() {
    var indexPagging : MutableLiveData<Int> = MutableLiveData( 1 )
    var listMovie: MutableLiveData<List<DataMovieEntity>> = MutableLiveData(listOf())

    fun getDataMovieByGenre(ctx:Context,idGenre:String){
        val apiKey = ctx.getString(R.string.moviedb_key)
        ApiConfig.getApiService().getMovieByGenreList(apiKey,idGenre).enqueue(object : Callback<ResponseListMovie> {
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