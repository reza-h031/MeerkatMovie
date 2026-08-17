package com.rezahosseini.meerkatmovie.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rezahosseini.meerkatmovie.model.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SElECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert()
    suspend fun insertMovie(movie:MovieEntity)
    @Delete()
    suspend fun deleteMovie(movie: MovieEntity)
}