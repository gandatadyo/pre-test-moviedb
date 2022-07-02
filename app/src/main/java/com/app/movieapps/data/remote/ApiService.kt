package com.app.movieapps.data.remote

import com.app.movieapps.data.DataReviewEntity
import com.app.movieapps.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/genre/movie/list")
    fun getGenreMovieList(
        @Query("api_key") apiKey:String
    ): Call<ResponseGenre>

    @GET("/3/discover/movie?language=en-US")
    fun getMovieList(
        @Query("api_key") apiKey:String,
    ): Call<ResponseListMovie>

    @GET("/3/discover/movie?language=en-US")
    fun getMovieByGenreList(
        @Query("api_key") apiKey:String,
        @Query("with_genres") idGenre:String
    ): Call<ResponseListMovie>

    @GET("/3/movie/{movie_id}?language=en-US")
    fun getMovieDetail(
        @Path("movie_id") idMovie:String,
        @Query("api_key") apiKey:String
    ): Call<ResponseDetailMovie>

    @GET("/3/movie/{movie_id}/reviews?language=en-US&page=1")
    fun getReviews(
        @Path("movie_id") idMovie:String,
        @Query("api_key") apiKey:String
    ): Call<ResponseReviewMovie>

    @GET("/3/movie/{movie_id}/videos")
    fun getVideosUrl(
        @Path("movie_id") idMovie:String,
        @Query("api_key") apiKey:String
    ): Call<ResponseVideos>



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