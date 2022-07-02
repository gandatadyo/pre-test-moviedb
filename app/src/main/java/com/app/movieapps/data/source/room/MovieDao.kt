package com.app.movieapps.data.source.room

import androidx.room.*
import com.app.movieapps.data.source.entity.ItemMovieEntity

@Dao
interface MovieDao {

    // for movie data
//    @Query("SELECT * FROM movieentities")
//    fun getMovies(): DataSource.Factory<Int, ItemMovieEntity>
//
//    @RawQuery(observedEntities = [ItemMovieEntity::class])
//    fun getBookmarkedMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, ItemMovieEntity>
//
//    @Transaction
//    @Query("SELECT * FROM movieentities WHERE itemId = :itemID")
//    fun getMovieDetail(itemID: String): LiveData<ItemMovieEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMovies(movies: List<ItemMovieEntity>)
//
//    @Update
//    fun updateMovie(movie: ItemMovieEntity)
//
//    // for tv data
//    @Query("SELECT * FROM tventities")
//    fun getTvs(): DataSource.Factory<Int, ItemTvEntity>
//
//    @RawQuery(observedEntities = [ItemTvEntity::class])
//    fun getBookmarkedTv(query: SupportSQLiteQuery): DataSource.Factory<Int, ItemTvEntity>
//
//    @Transaction
//    @Query("SELECT * FROM tventities WHERE itemId = :itemID")
//    fun getTvDetail(itemID: String): LiveData<ItemTvEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertTvs(tvs: List<ItemTvEntity>)
//
//    @Update
//    fun updateTv(tv: ItemTvEntity)

}