package com.app.movieapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.movieapps.api.ApiConfig
import com.app.movieapps.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnTest.setOnClickListener {
//            ApiConfig.getApiService().getExample("data").enqueue(object : Callback<ResponseExample> {
//                override fun onResponse(
//                    call: Call<ResponseExample>,
//                    response: Response<ResponseExample>
//                ) {
//                    if (response.isSuccessful) {
////                        val responseBody = response.body()
////                        if(responseBody!!.status=="true") {
////                            listDataMovie.clear()
////                            if (responseBody != null) {
////                                for (i in 0 until responseBody.data.size) {
////                                    listDataMovie.add(
////                                        responseBody.data[i]!!
////                                    )
////                                }
////                            }
////                            adapterMovie.notifyDataSetChanged()
////                        }else{
////                            Toast.makeText(this@ExamRetrofit, responseBody.message,Toast.LENGTH_LONG).show()
////                        }
//                    } else {
//                        Log.e(TAG, "onFailure: ${response.message()}")
//                    }
//                }
//                override fun onFailure(call: Call<ResponseExample>, t: Throwable) {
//                    Log.e(TAG, "onFailure: ${t.message}")
//                }
//            })
        }
    }
}