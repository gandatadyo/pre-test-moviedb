package com.app.movieapps.data.remote

import com.app.movieapps.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // example
//    @GET("3/movie/550?api_key={apikey}")
//    fun getExample(
//        @Query("apikey") apiKey:String
//    ): Call<ResponseExample>

    // genre movie
    @GET("3/genre/movie/list?api_key={{apikey}}&language=en-US")
    fun getGenreMovieList(
        @Query("apikey") apiKey:String
    ): Call<DataGenre>

    // movie list
    @GET("/3/discover/movie?api_key={{apikey}}&language=en-US&with_genres={idGenre}")
    fun getMovieList(
        @Query("apikey") apiKey:String,
        @Query("idGenre") idGenre:String
    ): Call<DataListMovie>

    // movie detail
    @GET("/3/movie/{idMovie}?api_key={{apikey}}&language=en-US")
    fun getMovieDetail(
        @Path("idMovie") idMovie:String,
        @Query("apikey") apiKey:String
    ): Call<DataDetailMovie>

    // movie reviews
    @GET("/3/movie/{idMovie}/reviews?api_key={{apikey}}&language=en-US&page=1")
    fun getReviews(
        @Path("idMovie") idMovie:String,
        @Query("apikey") apiKey:String
    ): Call<DataReviewMovie>


//    @GET("detail_data/{id}")
//    fun getDetailMovie(
//        @Path("id") id: String
//    ): Call<ResponseDetailData>

//    @FormUrlEncoded
//    @Headers("Authorization: token 12345")
//    @POST("insert_data")
//    fun insertMovie(
//        @Field("title") title: String,
//    ): Call<ResponseInfo>
//
//    @FormUrlEncoded
//    @Headers("Authorization: token 12345")
//    @POST("update_data")
//    fun updateMovie(
//        @Field("id") id: String,
//        @Field("title") title: String,
//    ): Call<ResponseInfo>

}